package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(HomePageCommand.class);
    private static final String LANGUAGE = "language";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String language = (String) request.getSession().getAttribute(LANGUAGE);
        if (language == null) {
            request.getSession().setAttribute(LANGUAGE, "en");
        }
        String page = ResourceManager.getProperty("page.main");
        return new CommandResult(page);
    }
}
