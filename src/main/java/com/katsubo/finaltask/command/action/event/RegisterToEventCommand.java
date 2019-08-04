package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.command.ResourceManager;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Registration;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.enums.Role;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.impl.RegistrationServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterToEventCommand implements Command {
    private static final String CANT_FIND_EVENT_ID = "cant_find_event_id";
    private static final Logger logger = LogManager.getLogger(RegisterToEventCommand.class);
    private static final String EVENT_ID = "eventId";
    private static final String CANT_SAVE_REGISTRATION = "cant_save_registration";
    private static final String DONE = "register_done";
    private static final String SUCH_REGISTRATION_ALREADY_EXIST = "such_registration_already_exist";
    public static final String FREE_PLACES = "freePlaces";
    public static final String NO_FREE_PLACES = "no_free_places";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Integer eventId;
        Integer freePlaces = Integer.valueOf(request.getParameter(FREE_PLACES));

        if (request.getParameter(EVENT_ID) == null) {
            if (request.getAttribute(EVENT_ID) == null){
                logger.log(Level.WARN, "can't find eventId");
                return failure(CANT_FIND_EVENT_ID, request);
            } else{
                eventId = Integer.valueOf((String) request.getAttribute(EVENT_ID));
            }

            if (freePlaces == null || freePlaces < 1){
                logger.log(Level.WARN, "there's no free places on this conference!");
                return failure(NO_FREE_PLACES, request);
            }
        } else {
            eventId = Integer.valueOf(request.getParameter(EVENT_ID));
        }
        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        Integer userId = user.getUserId();
        Registration registration = new Registration(userId, eventId, Role.LISTENER);
        try {
            if (!registrationExist(eventId, userId)) {
                register(registration);
            }else{
                throw new DaoException(SUCH_REGISTRATION_ALREADY_EXIST);
            }
        } catch (DaoException | ServiceException e) {
            logger.log(Level.WARN, e);
            return failure(CANT_FIND_EVENT_ID, request);
        }
        request.setAttribute(DONE, true);
        return new CommandResult(ResourceManager.getProperty("command.home"));

    }

    private boolean registrationExist(Integer eventId, Integer userId) throws DaoException, ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        Registration registration = service.readByUserAndEvent(eventId, userId);
        return registration != null;
    }

    private void register(Registration registration) throws DaoException, ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        Integer id = service.save(registration);
        if (id == null){
            throw new ServiceException(CANT_SAVE_REGISTRATION);
        }
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        return new CommandResult(ResourceManager.getProperty("command.home"));
    }
}
