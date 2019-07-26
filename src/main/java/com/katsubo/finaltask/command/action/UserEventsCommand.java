package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ConfigurationManager;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.RegistrationServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserEventsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UserEventsCommand.class);
    private static final String CANT_FIND_USER = "cant_find_user";
    private static final String ERROR_FIND_USER_EVENTS = "error_find_user_events";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserDto userDto = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        UserInfo info = null;
        List<Event> events = null;
        if (userDto != null) {
            try {
                events = findUserEvents(userDto);
            } catch (DaoException | ServiceException e) {
                logger.log(Level.INFO, e.getMessage());
                goBackWithError(request, e.getMessage());
            }
            setAttributes(events, request);
            return new CommandResult(ConfigurationManager.getProperty("path.page.main"), false);
        } else {
            logger.log(Level.INFO, CANT_FIND_USER);
            return goBackWithError(request, CANT_FIND_USER);
        }

    }

    private List<Event> findUserEvents(UserDto userDto) throws ServiceException, DaoException {
        RegistrationService service = new RegistrationServiceImpl();
        List<Event> events = service.findUserEvents(userDto.getUserId());
        if (events != null) {
            return events;
        } else {
            throw new DaoException(ERROR_FIND_USER_EVENTS);
        }
    }

    private void setAttributes(List<Event> events, HttpServletRequest request) {
        request.setAttribute(Constances.INCLUDE.getFieldName(), ConfigurationManager.getProperty("path.page.userEvents"));
        request.getSession().setAttribute("events", events);
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult("/controller?command=home_page", false);
    }
}
