package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.Constances;
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

public class EditUserCommand implements ActionCommand {
    private static final String ERROR_UPDATE_USER = "error_update_user";
    private static final String INCORRECT_VERIFY_PASSWORD = "incorrect_verify_password";
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "password2";
    private static final String DONE = "done";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        clearWarnings(request);
        UserDto userDto = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String repeatPassword = request.getParameter(REPEAT_PASSWORD);

        User user = new User();
        user.setId(userDto.getUserId());
        user.setLogin(login);

        if (!password.equals(repeatPassword)) {
            logger.log(Level.INFO, "Verify password is incorrect");
            return goBackWithError(INCORRECT_VERIFY_PASSWORD, request);
        } else if (!password.isEmpty() && !repeatPassword.isEmpty()) {
            user.setPassword(password);
        }
        user.setPermission(userDto.getPermission());
        try {
            update(user);
        } catch (DaoException e) {
            logger.log(Level.INFO, e.getMessage());
            return goBackWithError(e.getMessage(), request);
        }
        setAttributesToSession(user, request);

        return new CommandResult("/controller?command=profile", false);
    }

    private void clearWarnings(HttpServletRequest request) {
        request.removeAttribute(DONE);
        request.removeAttribute(ERROR_UPDATE_USER);
        request.removeAttribute(INCORRECT_VERIFY_PASSWORD);
    }

    private void update(User user) throws DaoException, ServiceException {
        UserService service = new UserServiceImpl();
        Integer id = service.save(user);
        if (id == null) {
            throw new ServiceException(ERROR_UPDATE_USER);
        }
    }

    private void setAttributesToSession(User user, HttpServletRequest request) {
        request.setAttribute(DONE, true);
        HttpSession session = request.getSession();
        UserDto userDto = new UserDto(user);
        session.setAttribute(Constances.USER.getFieldName(), userDto);

    }

    private CommandResult goBackWithError(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        return new CommandResult("/controller?command=profile", false);
    }
}
