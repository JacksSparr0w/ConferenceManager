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

public class EditUserPhotoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final String USER_PHOTO = "userPhoto";
    private static final String ERROR_UPLOAD_USER_PHOTO = "error_upload_user_photo";
    private static final String DONE = "success_upload_user_photo";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserInfo info = (UserInfo) request.getSession().getAttribute(Constances.USER_INFO.getFieldName());

        try {
            Part part = request.getPart(USER_PHOTO);
            if (part.getSize() > 0){
                String fileName = DigestUtils.md2Hex(info.getUser().getId().toString()) + "." + "jpg";
                String path = getPath();
                File file = new File(path + ConfigurationManager.getProperty("path.imageDirectory") + fileName);
                if (ImageIO.write(ImageIO.read(part.getInputStream()), "jpg", file)) {
                    info.setPictureLink(fileName);
                } else {
                    return goBackWithError(ERROR_UPLOAD_USER_PHOTO, request);
                }
            }
        } catch (IOException | ServletException | NullPointerException e) {
            logger.log(Level.WARN, e.getMessage());
            return goBackWithError(ERROR_UPLOAD_USER_PHOTO, request);
        }

        try {
            update(info);
        } catch (DaoException | ServiceException e) {
            logger.log(Level.INFO, ERROR_UPLOAD_USER_PHOTO);
            return goBackWithError(ERROR_UPLOAD_USER_PHOTO, request);
        }
        request.setAttribute(DONE, true);
        HttpSession session = request.getSession();
        session.setAttribute(Constances.USER_INFO.getFieldName(), info);

        return new CommandResult("/controller?command=profile", false);
    }

    private String getPath() {
        String path = getClass().getClassLoader().getResource("").getPath();
        String[] pathArr = path.split("/WEB-INF/classes/");
        return pathArr[0];
    }

    private void update(UserInfo info) throws DaoException, ServiceException {
        UserInfoService service = new UserInfoServiceImpl();
        service.save(info);
    }

    private CommandResult goBackWithError(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        //request.setAttribute(Constances.INCLUDE.getFieldName(), ConfigurationManager.getProperty("page.profile"));
        return new CommandResult("/controller?command=profile", false);
    }
}
