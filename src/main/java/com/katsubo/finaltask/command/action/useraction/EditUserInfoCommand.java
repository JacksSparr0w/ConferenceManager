package com.katsubo.finaltask.command.action.useraction;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserInfoService;
import com.katsubo.finaltask.service.impl.UserInfoServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.util.repair.UserInfoRecover;
import com.katsubo.finaltask.validate.UserInfoValidator;
import com.katsubo.finaltask.validate.Validator;
import com.katsubo.finaltask.validate.ValidatorException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Edit user info command.
 */
public class EditUserInfoCommand implements Command {
    private static final String ERROR = "error";
    private static final String INFO_EDIT_SUCCESS = "info.edit.success";
    private static final String ERROR_UPDATE_USER_INFO = "info.edit.fail";
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String ABOUT = "about";
    private static final String DONE = "done";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserInfo info = (UserInfo) request.getSession().getAttribute(Constances.USER_INFO.getFieldName());
        String name = request.getParameter(NAME);
        info.setName(name);
        String surname = request.getParameter(SURNAME);
        info.setSurname(surname);
        String about = request.getParameter(ABOUT);
        info.setAbout(about);
        String email = request.getParameter(EMAIL);
        info.setEmail(email);
        String date = request.getParameter(DATE_OF_BIRTH);

        if (date == null || date.isEmpty()) {
            logger.log(Level.WARN, ERROR_UPDATE_USER_INFO);
            return failure(ERROR_UPDATE_USER_INFO, request);
        }
        Date parsed;
        try {
            parsed = format.parse(request.getParameter(DATE_OF_BIRTH));
        } catch (ParseException pe) {
            logger.log(Level.WARN, ERROR_UPDATE_USER_INFO);
            return failure(ERROR_UPDATE_USER_INFO, request);
        }
        info.setDateOfBirth(parsed);

        recover(info);
        try {
            if (valid(info)) {
                update(info);
            }
        } catch (ValidatorException | ServiceException e) {
            logger.log(Level.WARN, ERROR_UPDATE_USER_INFO);
            return failure(ERROR_UPDATE_USER_INFO, request);
        }

        request.getSession().setAttribute(DONE, INFO_EDIT_SUCCESS);
        HttpSession session = request.getSession();
        session.setAttribute(Constances.USER_INFO.getFieldName(), info);

        return new CommandResult(ResourceManager.getProperty("command.profile"), true);

    }

    private boolean valid(UserInfo info) throws ValidatorException {
        Validator validator = new UserInfoValidator();
        String error = validator.isValid(info);
        if (error != null) {
            throw new ValidatorException(error);
        } else {
            return true;
        }
    }

    private void recover(UserInfo info) {
        new UserInfoRecover().recover(info);
    }

    private void update(UserInfo info) throws ServiceException {
        UserInfoService service = new UserInfoServiceImpl();
        service.save(info);
    }


    private CommandResult failure(String error, HttpServletRequest request) {
        request.getSession().setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.profile"), true);

    }
}
