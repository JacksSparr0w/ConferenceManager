package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ConfigurationManager;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.enums.Permission;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import com.katsubo.finaltask.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserCommand implements ActionCommand {
    public static final String ERROR_UPDATE_USER = "error_update_user";
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "password2";
    public static final String INCORRECT_VERIFY_PASSWORD = "incorrect_verify_password";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserDto userDto = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String repeatPassword = request.getParameter(REPEAT_PASSWORD);

        if (!password.equals(repeatPassword)){
            logger.log(Level.INFO, "Verify password is incorrect");
            return goBackWithError(INCORRECT_VERIFY_PASSWORD, request);
        }

        User user = new User();
        user.setId(userDto.getUserId());
        user.setLogin(login);
        user.setPassword(password);
        user.setPermission(userDto.getPermission());

        try {
            update(user);
        } catch (DaoException e) {
            logger.log(Level.INFO, e.getMessage());
            return goBackWithError(e.getMessage(), request);
        }
        setAttributesToSession(user, request);

        return new CommandResult(ConfigurationManager.getProperty("path.page.main"), false);
    }

    private void update(User user) throws DaoException, ServiceException {
        UserService service = new UserServiceImpl();
        Integer id = service.save(user);
        if (id == null) {
            throw new ServiceException(ERROR_UPDATE_USER);
        }
    }

    private void setAttributesToSession(User user, HttpServletRequest request) {
        //include fragment into main page
        request.setAttribute(Constances.INCLUDE.getFieldName(), ConfigurationManager.getProperty("path.page.profile"));
        HttpSession session = request.getSession();
        UserDto userDto = new UserDto(user);
        session.setAttribute(Constances.USER.getFieldName(), userDto);
    }

    private CommandResult goBackWithError(String errorUpdateUser, HttpServletRequest request) {
        request.setAttribute(ERROR_UPDATE_USER, true);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ConfigurationManager.getProperty("path.page.profile"));
        return new CommandResult(ConfigurationManager.getProperty("path.page.main"), true);
    }
}
