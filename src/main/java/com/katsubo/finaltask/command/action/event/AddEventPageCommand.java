package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.command.ResourceManager;
import com.katsubo.finaltask.command.action.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddEventPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.addEvent"));
        String page = ResourceManager.getProperty("page.main");
        return new CommandResult(page);
    }
}
