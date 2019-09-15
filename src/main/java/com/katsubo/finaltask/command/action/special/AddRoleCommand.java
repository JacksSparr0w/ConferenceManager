package com.katsubo.finaltask.command.action.special;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Value;
import com.katsubo.finaltask.service.RoleService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.RoleServiceImpl;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddRoleCommand implements Command {
    private static final String ROLE_ADD_SUCCESS = "role.add.success";
    private static final String DONE = "done";
    private static final Logger logger = LogManager.getLogger(AddRoleCommand.class);
    private static final String ROLE = "role";
    private static final String INVALID_INPUT = "invalid.input";
    private static final String ERROR = "error";
    private static final String ERROR_SAVE_ROLE = "error.save.role";

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
        String role = request.getParameter(ROLE);
        if (role == null || role.isEmpty()) {
            logger.log(Level.WARN, INVALID_INPUT);
            return failure(request, INVALID_INPUT);
        }
        try {
            saveRole(role);
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(request, ERROR_SAVE_ROLE);
        }
        request.getSession().setAttribute(DONE, ROLE_ADD_SUCCESS);
        return new CommandResult(ResourceManager.getProperty("command.addRolePage"), true);
    }

    private void saveRole(String role) throws ServiceException {
        RoleService service = new RoleServiceImpl();
        service.save(new Value(role));
    }

    private CommandResult failure(HttpServletRequest request, String error) {
        request.getSession().setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.addRolePage"), true);

    }

}
