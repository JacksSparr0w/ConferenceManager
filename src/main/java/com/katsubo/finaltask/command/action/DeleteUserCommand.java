package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import com.katsubo.finaltask.service.impl.UserServiceImpl;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);

    private static final String USER_ID = "userId";
    private static final String ERROR_DONT_FIND_USER = "error_dont_find_user";
    private static final String ERROR_REMOVING_USER = "error_removing_user";
    private static final String DELETE_SUCCESS = "delete_success";
    public static final String ERROR = "error";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if (request.getParameter(USER_ID) == null) {
            logger.log(Level.WARN, ERROR_DONT_FIND_USER);
            return failure(ERROR_DONT_FIND_USER, request);
        }
        Integer userId;
        try {
            userId = Integer.valueOf(request.getParameter(USER_ID));
            deleteUser(userId);
        } catch (NumberFormatException e){
            logger.log(Level.WARN, ERROR_DONT_FIND_USER);
            return  failure(ERROR_DONT_FIND_USER, request);
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(ERROR_REMOVING_USER, request);
        }
        request.setAttribute(DELETE_SUCCESS, true);
        return new CommandResult(ResourceManager.getProperty("command.allUsers"));


    }

    private void deleteUser(Integer userId) throws ServiceException {
        UserService service = new UserServiceImpl();
        service.delete(userId);
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.allUsers"));
    }
}
