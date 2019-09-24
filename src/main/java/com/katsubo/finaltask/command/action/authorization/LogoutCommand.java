package com.katsubo.finaltask.command.action.authorization;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.katsubo.finaltask.util.Constances.*;

/**
 * The type Logout command.
 */
public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(PERMISSION.getFieldName());
        session.removeAttribute(USER.getFieldName());
        session.removeAttribute(MENU.getFieldName());
        return new CommandResult(ResourceManager.getProperty("command.home"), true);
    }
}
