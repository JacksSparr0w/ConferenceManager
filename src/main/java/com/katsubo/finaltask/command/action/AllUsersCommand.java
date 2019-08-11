package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserInfoService;
import com.katsubo.finaltask.service.UserService;
import com.katsubo.finaltask.service.impl.UserInfoServiceImpl;
import com.katsubo.finaltask.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AllUsersCommand.class);

    private static final String USERS = "users";
    private static final String USERS_INFO = "users_info";
    private static final String ERROR_FIND_USERS = "error_find_users";
    private static final String ERROR_FIND_USERS_INFO = "error_find_users_info";
    public static final String ERROR = "error";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<User> users = null;
        try {
            users = giveUsers();
        } catch (ServiceException e) {
            logger.log(Level.WARN, ERROR_FIND_USERS);
            return failure(ERROR_FIND_USERS, request);
        }

        Map<Integer, UserInfo> usersInfo;
        try{
            usersInfo = giveUsersInfo(users);
        } catch (ServiceException e){
            logger.log(Level.WARN, ERROR_FIND_USERS_INFO);
            return failure(ERROR_FIND_USERS_INFO, request);
        }

        request.getSession().setAttribute(USERS, users);
        request.getSession().setAttribute(USERS_INFO, usersInfo);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.allUsers"));
        return new CommandResult(ResourceManager.getProperty("page.main"));
    }

    private Map<Integer, UserInfo> giveUsersInfo(List<User> users) throws ServiceException {
        Map<Integer, UserInfo> usersInfo = new HashMap<>();
        UserInfoService service = new UserInfoServiceImpl();
        for (User user : users){
            usersInfo.put(user.getId(), service.findByUser(user));
        }
        return usersInfo;
    }

    private List<User> giveUsers() throws ServiceException {
        UserService service = new UserServiceImpl();
        return service.findAll();
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(ERROR, error);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.allUsers"));
        return new CommandResult(ResourceManager.getProperty("page.main"));
    }
}
