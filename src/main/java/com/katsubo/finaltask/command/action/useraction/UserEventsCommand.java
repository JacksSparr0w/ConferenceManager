package com.katsubo.finaltask.command.action.useraction;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.command.action.Command;
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

public class UserEventsCommand implements Command {
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
            } catch (ServiceException e) {
                logger.log(Level.WARN, e.getMessage());
                failure(request, e.getMessage());
            }
            request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.userEvents"));
            request.getSession().setAttribute("events", events);
            return new CommandResult(ResourceManager.getProperty("page.main"));
        } else {
            logger.log(Level.WARN, CANT_FIND_USER);
            return failure(request, CANT_FIND_USER);
        }

    }

    private List<Event> findUserEvents(UserDto userDto) throws ServiceException{
        RegistrationService service = new RegistrationServiceImpl();
        List<Event> events = service.findUserEvents(userDto.getUserId());
        if (events != null) {
            return events;
        } else {
            throw new ServiceException(ERROR_FIND_USER_EVENTS);
        }
    }

    private CommandResult failure(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ResourceManager.getProperty("page.main"));
    }
}
