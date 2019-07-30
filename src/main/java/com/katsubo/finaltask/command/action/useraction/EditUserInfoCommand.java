package com.katsubo.finaltask.command.action.useraction;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.command.action.ActionCommand;
import com.katsubo.finaltask.command.repair.Recover;
import com.katsubo.finaltask.command.repair.UserInfoRecover;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserInfoService;
import com.katsubo.finaltask.service.impl.UserInfoServiceImpl;
import com.katsubo.finaltask.validate.UserInfoValidator;
import com.katsubo.finaltask.validate.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditUserInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String ABOUT = "about";
    private static final String ERROR_UPDATE_USER_INFO = "error_update_user_info";
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

        if (date == null || date.isEmpty()){
            logger.log(Level.INFO, "Profile edit error");
            return goBackWithError(ERROR_UPDATE_USER_INFO, request);
        }
        Date parsed;
        try {
            parsed = format.parse(request.getParameter(DATE_OF_BIRTH));
        } catch (ParseException pe) {
            logger.log(Level.INFO, "Profile edit error");
            return goBackWithError(ERROR_UPDATE_USER_INFO, request);
        }
        info.setDateOfBirth(parsed);

        validate(info);
        try {
            update(info);
        } catch (DaoException | ServiceException e) {
            logger.log(Level.INFO, "Profile edit error");
            return goBackWithError(ERROR_UPDATE_USER_INFO, request);
        }

        request.setAttribute(DONE, true);
        HttpSession session = request.getSession();
        session.setAttribute(Constances.USER_INFO.getFieldName(), info);

        return new CommandResult("controller?command=profile", false);
    }

    private void validate(UserInfo info) {
        Validator validator = new UserInfoValidator();
        Recover recover = new UserInfoRecover();
        if (!validator.isValid(info)) {
            recover.recover(info);
        }

    }

    private void update(UserInfo info) throws DaoException, ServiceException {
        UserInfoService service = new UserInfoServiceImpl();
        service.save(info);
    }


    private CommandResult goBackWithError(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        return new CommandResult("controller?command=profile", false);
    }
}
