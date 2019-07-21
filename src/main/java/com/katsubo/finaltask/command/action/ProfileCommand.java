package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ConfigurationManager;
import com.katsubo.finaltask.command.Constances;
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

public class ProfileCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ProfileCommand.class);
    private static final String ERROR_FIND_USER_DTO = "error_find_user_dto";
    private static final String ERROR_FIND_USER = "error_find_user";
    private static final String ERROR_FIND_USER_INFO = "error_find_userInfo";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserDto userDto = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        UserInfo info = null;
        if (userDto != null) {
            try {
                User user = findUser(userDto);
                info = findUserInfo(user);
            } catch (DaoException e) {
                logger.log(Level.INFO, e.getMessage());
                goBackWithError(request, e.getMessage());
            }
            setAttributes(info, request);
            return new CommandResult(ConfigurationManager.getProperty("path.page.main"), false);
        } else {
            logger.log(Level.INFO, ERROR_FIND_USER_DTO);
            return goBackWithError(request, ERROR_FIND_USER_DTO);
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

    private void setAttributes(UserInfo info, HttpServletRequest request) {
        request.setAttribute(Constances.INCLUDE.getFieldName(), ConfigurationManager.getProperty("path.page.profile"));
        request.getSession().setAttribute(Constances.USER_INFO.getFieldName(), info);
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

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.main"), false);
    }
}
