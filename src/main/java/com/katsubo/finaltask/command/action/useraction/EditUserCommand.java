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
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Edit user command.
 */
public class EditUserCommand implements Command {
    private static final String ERROR = "error";
    private static final String USER_EDIT_SUCCESS = "user.edit.success";
    private static final String ERROR_UPDATE_USER = "error_update_user";
    private static final String VERIFY_PASSWORD_IS_INCORRECT = "verify_password_is_incorrect";
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "password2";
    private static final String DONE = "done";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserDto userDto = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String repeatPassword = request.getParameter(REPEAT_PASSWORD);

        User user = new User();
        user.setId(userDto.getUserId());
        user.setLogin(login);

        if (!password.equals(repeatPassword)) {
            logger.log(Level.WARN, "Verify password is incorrect");
            return failure(VERIFY_PASSWORD_IS_INCORRECT, request);
        } else if (!password.isEmpty()) {
            user.setPassword(password);
            try {
                if (valid(user))
                    user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            } catch (ValidatorException e){
                logger.log(Level.WARN, e.getMessage());
                return failure(ERROR_UPDATE_USER, request);
            }
        }


        user.setPermission(userDto.getPermission());
        try {
            update(user);
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(ERROR_UPDATE_USER, request);
        }
        request.getSession().setAttribute(DONE, USER_EDIT_SUCCESS);
        userDto = new UserDto(user);
        request.getSession().setAttribute(Constances.USER.getFieldName(), userDto);
        return new CommandResult(ResourceManager.getProperty("command.profile"), true);
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

    private void update(User user) throws ServiceException {
        UserService service = new UserServiceImpl();
        Integer id = service.save(user);
        if (id == null) {
            throw new ServiceException(ERROR_UPDATE_USER);
        }
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.getSession().setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.profile"), true);
    }
}
