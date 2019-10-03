package com.katsubo.finaltask.command.action.special;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.entity.Rule;
import com.katsubo.finaltask.service.PermissionService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.PermissionServiceImpl;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class AddPermissionCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddPermissionCommand.class);
    private static final String NAME = "name";
    private static final String ERROR = "error";
    private static final String INVALID_INPUT = "save.permission.invalid.input";
    private static final String SAVE_PERMISSION_SUCCESS = "save.permission.success";
    private static final String SAVE_PERMISSION_ERROR = "save.permission.error";
    public static final String DONE = "done";

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
        List<Rule> results = new ArrayList<>();
        Arrays.stream(Rule.values())
                .filter(n -> request.getParameter(n.getValue()) != null)
                .forEach(results::add);
        String name = request.getParameter(NAME);
        if (name == null || name.isEmpty()) {
            logger.log(Level.WARN, INVALID_INPUT);
            return failure(INVALID_INPUT, request);
        }

        Permission permission = new Permission(name);
        EnumSet<Rule> rules = EnumSet.noneOf(Rule.class);
        rules.addAll(results);
        permission.setRules(rules);
        try {
            savePermission(permission);
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(SAVE_PERMISSION_ERROR, request);
        }

        request.getSession().setAttribute(DONE, SAVE_PERMISSION_SUCCESS);
        return new CommandResult(ResourceManager.getProperty("command.addPermissionPage"), true);
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.getSession().setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.addPermissionPage"), true);

    }

    private void savePermission(Permission permission) throws ServiceException {
        PermissionService service = new PermissionServiceImpl();
        service.save(permission);
    }
}
