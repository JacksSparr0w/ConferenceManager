package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.enums.Permission;
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

/**
 * The type Edit event page command.
 */
public class EditEventPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditEventPageCommand.class);
    private static final String EVENT_ID = "eventId";
    private static final String ERROR_FIND_EVENT = "error_find_event";
    private static final String EVENT = "event";
    private static final String ERROR = "error";
    private Event event;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String eventIdString = request.getParameter(EVENT_ID);
        if (eventIdString == null) {
            logger.log(Level.WARN, ERROR_FIND_EVENT);
            return failure(ERROR_FIND_EVENT, request);
        }
        try {
            UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
            Integer eventId = Integer.valueOf(request.getParameter(EVENT_ID));
            if (checkRules(user, eventId)) {
                request.setAttribute(EVENT, event);
                request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.editEvent"));
                String page = ResourceManager.getProperty("page.main");
                return new CommandResult(page);
            } else {
                throw new ServiceException();
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, ERROR_FIND_EVENT);
            return failure(ERROR_FIND_EVENT, request);
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(e.getMessage(), request);
        }

    }

    private boolean checkRules(UserDto user, Integer eventId) throws ServiceException {
        if (user == null) {
            return false;
        }
        EventService service = new EventServiceImpl();
        event = service.findById(eventId);
        if (user.getPermission() == Permission.ADMINISTRATOR) {
            return true;
        }

        if (event == null) {
            throw new ServiceException(ERROR_FIND_EVENT);
        }
        return event.getAuthor_id().equals(user.getUserId());
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("page.error405"));
    }
}
