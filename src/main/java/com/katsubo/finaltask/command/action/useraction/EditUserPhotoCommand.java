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
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Edit user photo command.
 */
public class EditUserPhotoCommand implements Command {
    private static final String ERROR = "error";
    private static final String USER_PHOTO_EDIT_SUCCESS = "user.photo.edit.success";
    private static final Logger logger = LogManager.getLogger(EditUserPhotoCommand.class);
    private static final String USER_PHOTO = "userPhoto";
    private static final String ERROR_UPLOAD_USER_PHOTO = "error_upload_user_photo";
    private static final String DONE = "done";
    private static final String INVALID_TYPE_OF_FILE = "invalid_type_of_file";
    private static final List<String> formats = new ArrayList<>();

    static {
        formats.add("jpg");
        formats.add("jpeg");
        formats.add("png");
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserInfo info = (UserInfo) request.getSession().getAttribute(Constances.USER_INFO.getFieldName());

        try {
            Part part = request.getPart(USER_PHOTO);
            if (part.getSize() > 0) {
                String path = getPath();
                String format = part.getContentType().substring(part.getContentType().lastIndexOf('/') + 1);
                String fileName = DigestUtils.md2Hex(info.getUser().getId().toString()) + "." + format;
                if (!formats.contains(format.toLowerCase())) {
                    logger.log(Level.WARN, INVALID_TYPE_OF_FILE);
                    return failure(INVALID_TYPE_OF_FILE, request);
                }
                File file = new File(path + ResourceManager.getProperty("path.userImageDirectory") + fileName);
                if (ImageIO.write(ImageIO.read(part.getInputStream()), format, file)) {
                    info.setPictureLink(fileName);
                } else {
                    return failure(ERROR_UPLOAD_USER_PHOTO, request);
                }
            } else {
                return new CommandResult(ResourceManager.getProperty("command.profile"), true);
            }
        } catch (IOException | ServletException | NullPointerException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(ERROR_UPLOAD_USER_PHOTO, request);
        }

        validate(info);
        try {
            update(info);
        } catch (ServiceException e) {
            logger.log(Level.WARN, ERROR_UPLOAD_USER_PHOTO);
            return failure(ERROR_UPLOAD_USER_PHOTO, request);
        }
        request.getSession().setAttribute(DONE, USER_PHOTO_EDIT_SUCCESS);
        request.getSession().setAttribute(Constances.USER_INFO.getFieldName(), info);

        return new CommandResult(ResourceManager.getProperty("command.profile"), true);
    }

    private void validate(UserInfo info) {
        new UserInfoRecover().recover(info);


    }

    private String getPath() {
        String path = getClass().getClassLoader().getResource("").getPath();
        String[] pathArr = path.split("/WEB-INF/classes/");
        return pathArr[0];
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
