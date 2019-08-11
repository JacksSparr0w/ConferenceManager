package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.EventServiceImpl;
import com.katsubo.finaltask.service.impl.RegistrationServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.util.page.EventPagination;
import com.katsubo.finaltask.util.page.Pagination;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllEventsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AllEventsCommand.class);
    private static final String THERE_NOT_EVENTS = "there_not_events";
    private static final String CANT_READ_EVENTS = "cant_read_events";
    private static final Integer NOTES_PER_PAGE = 5;
    private static final String PAGE = "page";
    private static final String INVALID_PAGE = "invalid_page";
    private static final String ERROR = "error";

    private Pagination pagination;


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<Event> events = null;
        try {
            events = readAllEvents();
        } catch (DaoException | ServiceException e) {
            logger.log(Level.INFO, e.getMessage());
            return failure(CANT_READ_EVENTS, request);
        }
        if (events.isEmpty()) {
            logger.log(Level.INFO, "there is not events yet!");
            request.setAttribute(THERE_NOT_EVENTS, true);
        }

        List<Event> userEvents = null;
        UserDto userDto = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        if (userDto != null) {
            try {
                userEvents = readUserEvents(userDto.getUserId());
            } catch (DaoException | ServiceException e) {
                logger.log(Level.INFO, e.getMessage());
                return failure(CANT_READ_EVENTS, request);
            }
            events.removeAll(userEvents);
        }
        Map<Integer, Integer> filling = new HashMap<>();
        for (Event event : events) {
            try {
                Integer numberOfUsers = readUsersOnEvent(event);
                filling.put(event.getId(), numberOfUsers);
            } catch (DaoException | ServiceException e) {
                logger.log(Level.INFO, e.getMessage());
                return failure(CANT_READ_EVENTS, request);
            }
        }


        request.setAttribute("events", getPage(events, request));
        request.setAttribute("countOfPages", pagination.getCountOfPages());
        request.setAttribute(PAGE, pagination.getPage());
        request.setAttribute("filling", filling);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.eventInfo"));
        return new CommandResult(ResourceManager.getProperty("page.main"));
    }

    private List<Event> getPage(List<Event> events, HttpServletRequest request) {
        String pageString = request.getParameter(PAGE);
        pagination = new EventPagination(NOTES_PER_PAGE);
        if (pageString != null) {
            try {
                int page = Integer.valueOf(pageString);
                return pagination.getPage(events, page);
            } catch (NumberFormatException e) {
                logger.log(Level.WARN, INVALID_PAGE);
            }
        }
        Integer page1 = 1;
        return pagination.getPage(events, 1);


    }

    private Integer readUsersOnEvent(Event event) throws DaoException, ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        Integer integer = service.findUsersOnEvent(event.getId()).size();
        if (integer == null) {
            throw new DaoException();
        }
        return integer;

    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(ERROR, error);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.eventInfo"));
        return new CommandResult(ResourceManager.getProperty("page.main"));
    }

    private List<Event> readUserEvents(Integer userId) throws DaoException, ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        List<Event> events = service.findUserEvents(userId);
        return events;
    }

    private List<Event> readAllEvents() throws ServiceException, DaoException {
        EventService service = new EventServiceImpl();
        List<Event> events = service.findAll();
        return events;
    }
}