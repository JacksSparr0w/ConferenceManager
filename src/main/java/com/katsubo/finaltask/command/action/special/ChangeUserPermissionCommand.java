package com.katsubo.finaltask.command.action.special;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import com.katsubo.finaltask.service.impl.UserServiceImpl;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserPermissionCommand implements Command {
    private static final String USER_ID = "userId";
    private static final String INVALID_INPUT = "invalid.input";
    private static final String PERMISSION_UPDATE_FAIL = "permission.update.fail";
    private static final String ERROR = "error";
    private static final String PERMISSION_UPDATE_SUCCESS = "permission.update.success";
    private static final String DONE = "done";
    private static final Logger logger = LogManager.getLogger(ChangeUserPermissionCommand.class);
    private static final String PERMISSION_ID = "permissionId";

    /**
     * Execute command result.
     *
     * @param request  the request
     * @param response the response
     * @return the command result
     * @throws CommandException the command exception
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String permissionIdString = request.getParameter(PERMISSION_ID);
        String userIdString = request.getParameter(USER_ID);

        Integer permissionId;
        Integer userId;
        try {
            permissionId = Integer.valueOf(permissionIdString);
            userId = Integer.valueOf(userIdString);
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, "invalid parameters!");
            return failure(request, INVALID_INPUT);
        }
        try {
            User user = findUser(userId);
            Permission newPermission = new Permission();
            newPermission.setId(permissionId);
            user.setPermission(newPermission);
            updateUser(user);
        } catch (ServiceException e) {
            logger.log(Level.WARN, PERMISSION_UPDATE_FAIL);
            return failure(request, PERMISSION_UPDATE_FAIL);
        }
        request.getSession().setAttribute(DONE, PERMISSION_UPDATE_SUCCESS);
        return new CommandResult(ResourceManager.getProperty("command.allUsers"), true);
    }

    private void updateUser(User user) throws ServiceException {
        UserService service = new UserServiceImpl();
        service.save(user);
    }

    private User findUser(Integer userId) throws ServiceException {
        UserService service = new UserServiceImpl();
        return service.findById(userId);
    }

    private CommandResult failure(HttpServletRequest request, String error) {
        request.getSession().setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.allUsers"), true);
    }
}
