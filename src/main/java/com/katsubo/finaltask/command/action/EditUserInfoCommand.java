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
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditUserInfoCommand implements ActionCommand {
    private static final String AVATAR = "avatar";
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
        UserInfo info = (UserInfo) request.getSession().getAttribute(Constances.USER_INFO.getFieldName());
        info.setName(request.getParameter(NAME));
        info.setSurname(request.getParameter(SURNAME));
        try {
            Part part = request.getPart(AVATAR);
            Long l = part.getSize();
            if (part.getSize() > 0){
                String type = part.getContentType();
                String fileName = DigestUtils.md2Hex(info.getUser().getId().toString()) + "." + "jpg";
                String path = getPath();
                File file = new File(path + ConfigurationManager.getProperty("path.imageDirectory") + fileName);
                if (ImageIO.write(ImageIO.read(part.getInputStream()), "jpg", file)) {
                    info.setPictureLink(fileName);
                } else {
                    throw new IOException("error upload image");
                }
            }
        } catch (IOException | ServletException | NullPointerException e) {
            logger.log(Level.WARN, e.getMessage());
        }

        if (request.getParameter(ABOUT) == null || request.getParameter(ABOUT).isEmpty()) {
            UserInfo oldInfo = (UserInfo) request.getSession().getAttribute(Constances.USER_INFO.getFieldName());
            info.setAbout(oldInfo.getAbout());
        } else {
            info.setAbout(request.getParameter(ABOUT));
        }
        String email = request.getParameter(EMAIL);
        //email validation
        info.setEmail(email);
        String date = request.getParameter(DATE_OF_BIRTH);
        Date parsed;
        try {
            SimpleDateFormat format =
                    new SimpleDateFormat("MM/dd/yyyy");
            parsed = format.parse(request.getParameter(DATE_OF_BIRTH));
        }
        catch(ParseException pe) {
            logger.log(Level.INFO, "Error editing profile");
            return goBackWithError(ERROR_UPDATE_USER_INFO, request);
        }

        try {
            update(info);
        } catch (DaoException | ServiceException e) {
            logger.log(Level.INFO, "Error editing profile");
            return goBackWithError(ERROR_UPDATE_USER_INFO, request);
        }
        setAttributesToSession(info, request);

        return new CommandResult("/controller?command=profile", false);
    }

    private void update(UserInfo info) throws DaoException, ServiceException {
        UserInfoService service = new UserInfoServiceImpl();
        service.save(info);
    }

    private void setAttributesToSession(UserInfo info, HttpServletRequest request) {
        request.setAttribute(DONE, true);
        HttpSession session = request.getSession();
        session.setAttribute(Constances.USER_INFO.getFieldName(), info);
    }

    private CommandResult goBackWithError(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ConfigurationManager.getProperty("page.profile"));
        return new CommandResult("/controller?command=profile", false);
    }

    private String getPath() {
        String path = getClass().getClassLoader().getResource("").getPath();
        String pathArr[] = path.split("/WEB-INF/classes/");
        return pathArr[0];
    }
}
