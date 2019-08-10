package com.katsubo.finaltask.command.action.useraction;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import com.katsubo.finaltask.service.impl.UserServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.validate.UserValidator;
import com.katsubo.finaltask.validate.Validator;
import com.katsubo.finaltask.validate.ValidatorException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserCommand implements Command {
    private static final String ERROR_UPDATE_USER = "error_update_user";
    private static final String INCORRECT_VERIFY_PASSWORD = "incorrect_verify_password";
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "password2";
    private static final String DONE = "done";
    public static final String ERROR = "error";
    public static final String USER_EDIT_SUCCESS = "user.edit.success";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        clearWarnings(request);
        UserDto userDto = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String repeatPassword = request.getParameter(REPEAT_PASSWORD);

        User user = new User();
        user.setId(userDto.getUserId());
        user.setLogin(login);

        if (!password.equals(repeatPassword)) {
            logger.log(Level.WARN, "Verify password is incorrect");
            return failure(INCORRECT_VERIFY_PASSWORD, request);
        } else if (!password.isEmpty()) {
            user.setPassword(password);
        }
        user.setPermission(userDto.getPermission());
        try {
            if (valid(user))
                update(user);
        } catch (ValidatorException | ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(ERROR_UPDATE_USER, request);
        }
        request.setAttribute(DONE, USER_EDIT_SUCCESS);
        HttpSession session = request.getSession();
        userDto = new UserDto(user);
        session.setAttribute(Constances.USER.getFieldName(), userDto);
        return new CommandResult(ResourceManager.getProperty("command.profile"));
    }

    private boolean valid(User user) throws ValidatorException {
        Validator validator = new UserValidator();
        String error = validator.isValid(user);
        if (error != null) {
            throw new ValidatorException(error);
        } else {
            return true;
        }
    }

    private void clearWarnings(HttpServletRequest request) {
        request.removeAttribute(DONE);
        request.removeAttribute(ERROR_UPDATE_USER);
        request.removeAttribute(INCORRECT_VERIFY_PASSWORD);
    }

    private void update(User user) throws ServiceException {
        UserService service = new UserServiceImpl();
        Integer id = service.save(user);
        if (id == null) {
            throw new ServiceException(ERROR_UPDATE_USER);
        }
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.profile"));
    }
}
