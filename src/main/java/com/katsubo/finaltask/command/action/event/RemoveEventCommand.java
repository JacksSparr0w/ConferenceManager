package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.EventServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveEventCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RemoveEventCommand.class);
    private static final String EVENT_ID = "eventId";
    private static final String ERROR_DONT_FIND_EVENT = "error_dont_find_event";
    private static final String ERROR_REMOVING_EVENT = "error_removing_event";
    private static final String DELETE_SUCCESS = "delete_success";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if (request.getParameter(EVENT_ID) == null) {
            logger.log(Level.WARN, ERROR_DONT_FIND_EVENT);
            return failure(ERROR_DONT_FIND_EVENT, request);
        }
        Integer eventId = Integer.valueOf(request.getParameter(EVENT_ID));
        try {
            if (checkRules(request)){
                deleteEvent(eventId);
            } else {
                throw new ServiceException();
            }
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(ERROR_REMOVING_EVENT, request);
        }
        request.setAttribute(DELETE_SUCCESS, true);
        return new CommandResult(ResourceManager.getProperty("command.allEvents"));


    }

    private boolean checkRules(HttpServletRequest request) throws ServiceException {
        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        if (user == null){
            return false;
        }
        EventService service = new EventServiceImpl();
        Event event = service.findById(Integer.valueOf(request.getParameter(EVENT_ID)));
        return event.getAuthor_id().equals(user.getUserId());
    }

    private void deleteEvent(Integer eventId) throws ServiceException {
        EventService service = new EventServiceImpl();
        service.delete(eventId);
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        return new CommandResult(ResourceManager.getProperty("page.error405"));
    }
}