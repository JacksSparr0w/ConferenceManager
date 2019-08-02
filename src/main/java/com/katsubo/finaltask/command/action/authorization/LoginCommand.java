package com.katsubo.finaltask.command.action.authorization;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ResourceManager;
import com.katsubo.finaltask.command.action.Command;
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

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR_LOGIN = "error_login";
    private static final String ERROR_PASSWORD = "error_password";
    private static final String ERROR_AUTHENTIFICATION = "error_authentification";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        if (login == null || login.isEmpty()) {
            logger.log(Level.WARN, "invalid login was received");
            return failure(request, ERROR_LOGIN);
        }
        if (password == null || password.isEmpty()) {
            return failure(request, ERROR_PASSWORD);
        }
        boolean userExist = false;
        try {
            userExist = initializeUser(login, password, request);
        } catch (DaoException | ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(request, e.getMessage());
        }
        if (userExist) {
            logger.log(Level.WARN, "user authorized with login - " + login);
            return new CommandResult(ResourceManager.getProperty("command.home"), true);
        } else {
            logger.log(Level.WARN, "user with such login and password doesn't exist");
            return failure(request, ERROR_AUTHENTIFICATION);
        }


    }

    private boolean initializeUser(String login, String password, HttpServletRequest request) throws DaoException, ServiceException {
        UserService service = new UserServiceImpl();
        User user = service.findByLoginAndPassword(login, password);
        if (user != null && user.getId() != null) {
            UserDto userDto = new UserDto(user);
            HttpSession session = request.getSession();
            session.setAttribute(USER.getFieldName(), userDto);
            return true;
        } else {
            return false;
        }
    }

    private CommandResult failure(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ResourceManager.getProperty("command.loginPage"));
    }
}