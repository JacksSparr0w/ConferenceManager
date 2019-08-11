package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Registration;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.RegistrationServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LeaveEventCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegisterToEventCommand.class);
    private static final String EVENT_ID = "eventId";
    private static final String DONE = "done";
    private static final String THERES_NO_SUCH_REGISTRAION = "theres_no_such_registraion";
    private static final String CANT_FIND_EVENT = "event.notfound";
    public static final String ERROR = "error";
    public static final String EVENT_LEAVE_SUCCESS = "event.leave.success";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
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
        } catch (NumberFormatException e){
            logger.log(Level.WARN, CANT_FIND_EVENT);
            failure(CANT_FIND_EVENT, request);
        } catch (ServiceException e) {
            logger.log(Level.WARN, e);
            return failure(THERES_NO_SUCH_REGISTRAION, request);
        }
        request.setAttribute(DONE, EVENT_LEAVE_SUCCESS);
        return new CommandResult(ResourceManager.getProperty("command.userEvents"));
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
        request.setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.userEvents"));
    }
}
