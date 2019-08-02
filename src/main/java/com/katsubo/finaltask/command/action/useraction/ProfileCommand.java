package com.katsubo.finaltask.command.action.useraction;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ResourceManager;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserInfoService;
import com.katsubo.finaltask.service.UserService;
import com.katsubo.finaltask.service.impl.UserInfoServiceImpl;
import com.katsubo.finaltask.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ProfileCommand.class);
    private static final String ERROR_FIND_USER_DTO = "error_find_user_dto";
    private static final String ERROR_FIND_USER = "error_find_user";
    private static final String ERROR_FIND_USER_INFO = "error_find_userInfo";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserDto userDto = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        UserInfo info = null;
        if (userDto != null) {
            try {
                User user = findUser(userDto);
                info = findUserInfo(user);
            } catch (DaoException | ServiceException e) {
                logger.log(Level.WARN, e.getMessage());
                failure(request, e.getMessage());
            }
            request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.profile"));
            request.getSession().setAttribute(Constances.USER_INFO.getFieldName(), info);
            return new CommandResult(ResourceManager.getProperty("page.main"));
        } else {
            logger.log(Level.WARN, ERROR_FIND_USER_DTO);
            return failure(request, ERROR_FIND_USER_DTO);
        }

    }

    private User findUser(UserDto userDto) throws ServiceException, DaoException {
        UserService service = new UserServiceImpl();
        User user = service.findById(userDto.getUserId());
        if (user != null){
            return user;
        } else {
            throw new DaoException(ERROR_FIND_USER);
        }
    }

    private UserInfo findUserInfo(User user) throws DaoException, ServiceException {
        UserInfoService service = new UserInfoServiceImpl();
        UserInfo info;
        info = service.findByUser(user);
        if (info != null) {
            return info;
        } else {
            throw new DaoException(ERROR_FIND_USER_INFO);
        }
    }

    private CommandResult failure(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ResourceManager.getProperty("command.home"));
    }
}
