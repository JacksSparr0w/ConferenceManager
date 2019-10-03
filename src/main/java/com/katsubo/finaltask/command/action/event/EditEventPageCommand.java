package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.command.factory.CommandType;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.Value;
import com.katsubo.finaltask.filter.AccessSystem;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.ThemeService;
import com.katsubo.finaltask.service.impl.EventServiceImpl;
import com.katsubo.finaltask.service.impl.ThemeServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The type Edit event page command.
 */
public class EditEventPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditEventPageCommand.class);
    private static final String EVENT_ID = "eventId";
    private static final String ERROR_FIND_EVENT = "error_find_event";
    private static final String EVENT = "event";
    private static final String ERROR = "error";
    private static final String THEMES = "themes";
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
            if (!checkRules(eventId, user)) {
                throw new ServiceException("Not access");

            }

            List<Value> themes = getThemes();
            request.setAttribute(THEMES, themes);

            request.setAttribute(EVENT, event);
            request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.editEvent"));
            String page = ResourceManager.getProperty("page.main");
            return new CommandResult(page);

        } catch (NumberFormatException e) {
            logger.log(Level.WARN, ERROR_FIND_EVENT);
            return failure(ERROR_FIND_EVENT, request);
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(e.getMessage(), request);
        }

    }

    private boolean checkRules(Integer eventId, UserDto user) throws ServiceException {
        EventService service = new EventServiceImpl();
        event = service.findById(eventId);
        if (user == null) return false;
        if (user.getUserId().equals(event.getAuthor_id())) return true;
        return AccessSystem.checkAccess(CommandType.DELETE_ANY_EVENT);
    }

    private List<Value> getThemes() throws ServiceException {
        ThemeService service = new ThemeServiceImpl();
        return service.findAll();
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("page.error405"));
    }
}
