package com.katsubo.finaltask.command.action.authorization;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.command.action.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.katsubo.finaltask.util.Constances.*;

/**
 * The type Logout command.
 */
public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id");
        String userRole = (String) session.getAttribute("role");
        logger.log(Level.INFO, "user was above: id:" + userId + " role:" + userRole);
        session.removeAttribute(ID.getFieldName());
        session.removeAttribute(ROLE.getFieldName());
        session.removeAttribute(USER.getFieldName());
        return new CommandResult(ResourceManager.getProperty("command.home"), true);
    }
}
