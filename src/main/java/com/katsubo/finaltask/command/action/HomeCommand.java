package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ConfigurationManager;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.EventServiceImpl;
import com.katsubo.finaltask.service.impl.RegistrationServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeCommand implements ActionCommand {
    private static final String LANGUAGE = "language";
    private static final Logger logger = LogManager.getLogger(HomeCommand.class);
    private static final String THERE_NOT_EVENTS = "there_not_events";
    private static final String CANT_READ_EVENTS = "cant_read_events";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<Event> events = null;
        try {
            events = readAllEvents();
        } catch (DaoException | ServiceException e) {
            logger.log(Level.INFO, e.getMessage());
            return goWithError(CANT_READ_EVENTS, request);
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
                return goWithError(CANT_READ_EVENTS, request);
            }
            events.removeAll(userEvents);
        }

        String language = (String) request.getSession().getAttribute(LANGUAGE);
        if (language == null) {
            request.getSession().setAttribute(LANGUAGE, "en");
        }

        request.setAttribute("events", events);
        return new CommandResult(ConfigurationManager.getProperty("page.main"));
    }

    private CommandResult goWithError(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("page.main"));
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
