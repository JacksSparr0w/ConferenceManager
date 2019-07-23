package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.Constances;
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

public class JoinToEventCommand implements ActionCommand {
    public static final String CANT_FIND_EVENT_ID = "cant_find_event_id";
    private static final Logger logger = LogManager.getLogger(JoinToEventCommand.class);
    private static final String EVENT_ID = "eventId";
    public static final String CANT_SAVE_REGISTRATION = "cant_save_registration";
    public static final String DONE = "done";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if (request.getParameter(EVENT_ID) == null) {
            logger.log(Level.INFO, "can't find eventId");
            return goBackWithError(CANT_FIND_EVENT_ID, request);
        }
        Integer eventId = Integer.valueOf(request.getParameter(EVENT_ID));
        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        Integer userId = user.getUserId();
        Registration registration = new Registration(userId, eventId, Role.LISTENER);
        try {
            register(registration);
        } catch (DaoException e) {
            logger.log(Level.INFO, e);
            return goBackWithError(CANT_FIND_EVENT_ID, request);
        }
        request.setAttribute(DONE, true);
        return new CommandResult("/controller?command=home_page");
    }

    private void register(Registration registration) throws DaoException, ServiceException {
        RegistrationService service = new RegistrationServiceImpl();
        Integer id = service.save(registration);
        if (id == null){
            throw new ServiceException(CANT_SAVE_REGISTRATION);
        }
    }

    private CommandResult goBackWithError(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        return new CommandResult("/controller?command=home_page");
    }
}
