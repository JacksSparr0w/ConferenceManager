package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.Registration;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.Value;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.EventServiceImpl;
import com.katsubo.finaltask.service.impl.RegistrationServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Register to event command.
 */
public class RegisterToEventCommand implements Command {
    /**
     * The constant ERROR.
     */
    public static final String ERROR = "error";
    private static final String NO_FREE_PLACES = "no_free_places";
    private static final String CANT_FIND_EVENT = "cant_find_event";
    private static final String CANT_FIND_EVENT_ID = "cant_find_event_id";
    private static final Logger logger = LogManager.getLogger(RegisterToEventCommand.class);
    private static final String EVENT_ID = "eventId";
    private static final String CANT_SAVE_REGISTRATION = "event.register.fail";
    private static final String DONE = "done";
    private static final String SUCH_REGISTRATION_ALREADY_EXIST = "such_registration_already_exist";
    private static final String EVENT_REGISTER_SUCCESS = "event.register.success";
    private static final String ROLE = "role";
    private static final String CANT_FIND_ROLE_ID = "cant_find_role_id";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        clearNotification(request);
        Integer eventId;
        Integer roleId;

        if (request.getParameter(EVENT_ID) == null) {
            logger.log(Level.WARN, "can't find eventId");
            return failure(CANT_FIND_EVENT_ID, request);
        }

        if (request.getParameter(ROLE) == null) {
            logger.log(Level.WARN, "can't find roleId");
            return failure(CANT_FIND_ROLE_ID, request);
        }
        try {
            roleId = Integer.valueOf(request.getParameter(ROLE));
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, "can't find roleId");
            return failure(CANT_FIND_ROLE_ID, request);
        }

        try {
            eventId = Integer.valueOf(request.getParameter(EVENT_ID));
            if (isBusy(eventId)) {
                throw new ServiceException();
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, CANT_FIND_EVENT);
            return failure(CANT_FIND_EVENT, request);
        } catch (ServiceException e) {
            logger.log(Level.WARN, "there's no free places on this conference!");
            return failure(NO_FREE_PLACES, request);
        }

        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        Integer userId = user.getUserId();
        Registration registration = new Registration(userId, eventId, new Value(roleId));
        try {
            if (!registrationExist(eventId, userId)) {
                register(registration);
            } else {
                logger.log(Level.WARN, SUCH_REGISTRATION_ALREADY_EXIST);
                return failure(SUCH_REGISTRATION_ALREADY_EXIST, request);
            }
        } catch (ServiceException e) {
            logger.log(Level.WARN, e);
            return failure(CANT_FIND_EVENT_ID, request);
        }
        request.getSession().setAttribute(DONE, EVENT_REGISTER_SUCCESS);
        return new CommandResult(ResourceManager.getProperty("command.allEvents"), true);

    }

    private void clearNotification(HttpServletRequest request) {
        request.getSession().removeAttribute(DONE);
        request.getSession().removeAttribute(ERROR);
    }

    private boolean isBusy(Integer eventId) throws ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        Integer busyPlaces = service.findUsersOnEvent(eventId).size();
        EventService eventService = new EventServiceImpl();
        Event event = eventService.findById(eventId);
        Integer capacity = null;
        if (event == null) {
            throw new ServiceException(CANT_FIND_EVENT);
        }
        capacity = event.getCapacity();
        return capacity <= busyPlaces;
    }

    private boolean registrationExist(Integer eventId, Integer userId) throws ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        Registration registration = service.readByUserAndEvent(eventId, userId);
        return registration != null;
    }

    private void register(Registration registration) throws ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        Integer id = service.save(registration);
        if (id == null) {
            throw new ServiceException(CANT_SAVE_REGISTRATION);
        }
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.getSession().setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.allEvents"), true);
    }
}
