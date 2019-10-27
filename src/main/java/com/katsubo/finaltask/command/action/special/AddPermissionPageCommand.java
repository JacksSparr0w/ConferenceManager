package com.katsubo.finaltask.command.action.special;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.service.PermissionService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.PermissionServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddPermissionPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddPermissionPageCommand.class);
    private static final String EXIST_PERMISSIONS = "existPermissions";

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
        List<Permission> permissions = new ArrayList<>();
        try {
            permissions = readExistPermissions();
        } catch (ServiceException e) {
            logger.log(Level.WARN, "Cant find all existing permissions");
        }
        request.setAttribute(EXIST_PERMISSIONS, permissions);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.addPermission"));
        String page = ResourceManager.getProperty("page.main");
        return new CommandResult(page);
    }

    private List<Permission> readExistPermissions() throws ServiceException {
        PermissionService service = new PermissionServiceImpl();
        return service.readAll();
    }
}
