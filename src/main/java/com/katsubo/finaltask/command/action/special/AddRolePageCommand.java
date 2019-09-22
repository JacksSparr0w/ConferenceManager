package com.katsubo.finaltask.command.action.special;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Value;
import com.katsubo.finaltask.service.RoleService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.RoleServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddRolePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddRolePageCommand.class);
    private static final String EXIST_ROLES = "existRoles";

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
        List<Value> existRoles = new ArrayList<>();
        try {
            existRoles = readExistRoles();
        } catch (ServiceException e){
            logger.log(Level.WARN, "Cant readByID existing roles");
        }
        request.setAttribute(EXIST_ROLES, existRoles);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.addRole"));
        String page = ResourceManager.getProperty("page.main");
        return new CommandResult(page);
    }

    private List<Value> readExistRoles() throws ServiceException {
        RoleService service = new RoleServiceImpl();
        return service.findAll();
    }
}
