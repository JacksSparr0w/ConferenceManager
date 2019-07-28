package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Registration;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.enums.Role;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.RegistrationServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LeaveEventCommand implements ActionCommand{
    private static final Logger logger = LogManager.getLogger(RegisterToEventCommand.class);
    private static final String EVENT_ID = "eventId";
    private static final String DONE = "register_done";
    private static final String THERES_NO_SUCH_REGISTRAION = "theres_no_such_registraion";
    private static final String CANT_FIND_EVENT = "cant_find_event";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if (request.getParameter(EVENT_ID) == null) {
            logger.log(Level.INFO, "can't find event");
            return goBackWithError(CANT_FIND_EVENT, request);
        }
        Integer eventId = Integer.valueOf(request.getParameter(EVENT_ID));
        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        Integer userId = user.getUserId();
        try {
            unregister(eventId, userId);
        } catch (DaoException | ServiceException e) {
            logger.log(Level.INFO, e);
            return goBackWithError(THERES_NO_SUCH_REGISTRAION, request);
        }
        request.setAttribute(DONE, true);
        return new CommandResult("/controller?command=user_events", false);
    }

    private void unregister(Integer eventId, Integer userId) throws DaoException, ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        Registration registration = service.readByUserAndEvent(eventId, userId);
        if (registration != null){
            service.delete(registration.getId());
        } else {
            throw new ServiceException(THERES_NO_SUCH_REGISTRAION);
        }
    }

    private CommandResult goBackWithError(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        return new CommandResult("/controller?command=user_events");
    }
}
