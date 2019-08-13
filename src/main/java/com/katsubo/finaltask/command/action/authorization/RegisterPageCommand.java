package com.katsubo.finaltask.command.action.authorization;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.command.action.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Register page command.
 */
public class RegisterPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = ResourceManager.getProperty("page.register");
        return new CommandResult(page);
    }
}
