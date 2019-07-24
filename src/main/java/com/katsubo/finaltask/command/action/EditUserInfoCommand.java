package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ConfigurationManager;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserInfoService;
import com.katsubo.finaltask.service.impl.UserInfoServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class EditUserInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String ABOUT = "about";
    private static final String ERROR_UPDATE_USER_INFO = "error_update_user_info";
    private static final String DONE = "done";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        clearWarnings(request);
        UserInfo info = (UserInfo) request.getSession().getAttribute(Constances.USER_INFO.getFieldName());
        info.setName(request.getParameter(NAME));
        info.setSurname(request.getParameter(SURNAME));
        if (request.getParameter(ABOUT).isEmpty()) {
            UserInfo oldInfo = (UserInfo) request.getSession().getAttribute(Constances.USER_INFO.getFieldName());
            info.setAbout(oldInfo.getAbout());
        } else {
            info.setAbout(request.getParameter(ABOUT));
        }
        String email = request.getParameter(EMAIL);
        //email validation
        info.setEmail(email);
        info.setDateOfBirth(new Date(request.getParameter(DATE_OF_BIRTH)));

        try {
            update(info);
        } catch (DaoException | ServiceException e) {
            logger.log(Level.INFO, "Error editing profile");
            return goBackWithError(ERROR_UPDATE_USER_INFO, request);
        }
        setAttributesToSession(info, request);

        return new CommandResult("/controller?command=profile", false);
    }

    private void clearWarnings(HttpServletRequest request) {
        request.removeAttribute(ERROR_UPDATE_USER_INFO);
    }

    private void update(UserInfo info) throws DaoException, ServiceException {
        UserInfoService service = new UserInfoServiceImpl();
        service.save(info);
    }

    private void setAttributesToSession(UserInfo info, HttpServletRequest request) {
        //include fragment into main page
        request.setAttribute(DONE, true);
        HttpSession session = request.getSession();
        session.setAttribute(Constances.USER_INFO.getFieldName(), info);
    }

    private CommandResult goBackWithError(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ConfigurationManager.getProperty("path.page.profile"));
        return new CommandResult("/controller?command=profile", false);
    }
}
