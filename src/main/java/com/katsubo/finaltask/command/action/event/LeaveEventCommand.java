package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Registration;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.RegistrationServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Leave event command.
 */
public class LeaveEventCommand implements Command {
    /**
     * The constant ERROR.
     */
    public static final String ERROR = "error";
    /**
     * The constant EVENT_LEAVE_SUCCESS.
     */
    public static final String EVENT_LEAVE_SUCCESS = "event.leave.success";
    private static final Logger logger = LogManager.getLogger(RegisterToEventCommand.class);
    private static final String EVENT_ID = "eventId";
    private static final String DONE = "done";
    private static final String THERES_NO_SUCH_REGISTRAION = "theres_no_such_registraion";
    private static final String CANT_FIND_EVENT = "event.notfound";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        clearNotification(request);
        if (request.getParameter(EVENT_ID) == null) {
            logger.log(Level.WARN, "can't find event");
            return failure(CANT_FIND_EVENT, request);
        }
        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        Integer userId = user.getUserId();
        Integer eventId;
        try {
            eventId = Integer.valueOf(request.getParameter(EVENT_ID));
            unregister(eventId, userId);
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, CANT_FIND_EVENT);
            failure(CANT_FIND_EVENT, request);
        } catch (ServiceException e) {
            logger.log(Level.WARN, e);
            return failure(THERES_NO_SUCH_REGISTRAION, request);
        }
        request.getSession().setAttribute(DONE, EVENT_LEAVE_SUCCESS);
        return new CommandResult(ResourceManager.getProperty("command.userEvents"), true);
    }

    private void clearNotification(HttpServletRequest request) {
        request.getSession().removeAttribute(DONE);
        request.getSession().removeAttribute(ERROR);
    }

    private void unregister(Integer eventId, Integer userId) throws ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        Registration registration = service.readByUserAndEvent(eventId, userId);
        if (registration != null) {
            service.delete(registration.getId());
        } else {
            throw new ServiceException(THERES_NO_SUCH_REGISTRAION);
        }
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.getSession().setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.userEvents"), true);
    }
}
