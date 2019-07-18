package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ConfigurationManager;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.entity.enums.Permission;
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
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(LOGIN, request.getParameter(LOGIN));
        parameters.put(PASSWORD, request.getParameter(PASSWORD));
        parameters.put(NAME, request.getParameter(NAME));
        parameters.put(SURNAME, request.getParameter(SURNAME));
        parameters.put(EMAIL, request.getParameter(EMAIL));

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                logger.log(Level.ERROR, "Invalid " + entry.getKey() + " was received");
                return goBackWithError(request, "error " + entry.getKey());
            }
        }

        boolean userExist = false;
        try {
            userExist = checkIfUserExist(parameters.get(LOGIN));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (userExist) {
            logger.log(Level.INFO, "user with such login and password already exist");
            return goBackWithError(request, "error_registration");
        }
        try {
            createUser(parameters, request);
            logger.log(Level.INFO, "user registrated and authorized with login - " + parameters.get(LOGIN));
            return new CommandResult("controller?command=home_page", true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }


    }

    private boolean checkIfUserExist(String login) throws DaoException, ServiceException {
        UserService service = new UserServiceImpl();
        return service.isExist(login);
    }

    private void createUser(Map<String, String> parameters, HttpServletRequest request) throws DaoException, ServiceException {
        User user = new User();
        user.setLogin(parameters.get(LOGIN));
        user.setPassword(parameters.get(PASSWORD));
        user.setPermission(Permission.USER);

        UserService service = new UserServiceImpl();
        Integer id = service.save(user);
        if (id != null) {
            user.setId(id);
        } else {
            throw new ServiceException("Can't save user!");
        }
        setAtributesToSession(user, request);

        createUserInfo(user, parameters);
    }

    private void createUserInfo(User user, Map<String, String> parameters) throws DaoException, ServiceException {
        UserInfo info = new UserInfo();
        info.setUser(user);
        info.setName(parameters.get(NAME));
        info.setSurname(parameters.get(SURNAME));
        info.setEmail(parameters.get(EMAIL));
        info.setDateOfRegistration(new Date());

        UserInfoService service = new UserInfoServiceImpl();
        service.save(info);
        //todo move to service layer

    }

    private void setAtributesToSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("role", user.getClass().getSimpleName().toLowerCase());
        session.setAttribute("user", user.getLogin());
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.register"), false);
    }
}
