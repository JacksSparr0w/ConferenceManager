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

public class EditEventPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditEventPageCommand.class);
    private static final String EVENT_ID = "eventId";
    private static final String ERROR_FIND_EVENT = "error_find_event";
    public static final String EVENT = "event";
    private Event event;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String eventIdString = request.getParameter(EVENT_ID);
        if (eventIdString == null){
            logger.log(Level.WARN, ERROR_FIND_EVENT);
            return failure(ERROR_FIND_EVENT, request);
        }
        try {
            if (checkRules(request)){
                request.setAttribute(EVENT, event);
                request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.editEvent"));
                String page = ResourceManager.getProperty("page.main");
                return new CommandResult(page);
            } else {
                throw new ServiceException();
            }
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(e.getMessage(), request);
        }

    }
    private boolean checkRules(HttpServletRequest request) throws ServiceException {
        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        if (user == null){
            return false;
        }
        EventService service = new EventServiceImpl();
        event = service.findById(Integer.valueOf(request.getParameter(EVENT_ID)));
        return event.getAuthor_id().equals(user.getUserId());
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        return new CommandResult(ResourceManager.getProperty("page.error405"));
    }
}
