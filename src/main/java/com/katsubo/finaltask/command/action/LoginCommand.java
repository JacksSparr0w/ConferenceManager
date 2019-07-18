package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ConfigurationManager;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import com.katsubo.finaltask.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.katsubo.finaltask.command.Constances.*;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        if (login == null || login.isEmpty()) {
            logger.log(Level.INFO, "invalid login was received");
            return goBackWithError(request, "error_login");//todo
        }
        if (password == null || password.isEmpty()) {
            logger.log(Level.INFO, "invalid password was received");
            return goBackWithError(request, "error_password");//todo
        }
        boolean userExist = false;
        try {
            userExist = initializeUser(login, password, request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (userExist) {
            logger.log(Level.INFO, "user authorized with login - " + login);
            return new CommandResult("controller?command=home_page", true);
        } else {
            logger.log(Level.INFO, "user with such login and password doesn't exist");
            return goBackWithError(request, "error_authentification");
        }


    }

    private boolean initializeUser(String login, String password, HttpServletRequest request) throws DaoException, ServiceException {
        UserService service = new UserServiceImpl();
        User user = service.findByLoginAndPassword(login, password);
        if (user != null && user.getId() != null) {
            setAtributesToSession(user, request);
            return true;
        } else {
            return false;
        }
    }

    private void setAtributesToSession(User user, HttpServletRequest request) {
        UserDto userDto = new UserDto(user);
        HttpSession session = request.getSession();
        session.setAttribute(USER.getFieldName(), userDto);
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }
}
