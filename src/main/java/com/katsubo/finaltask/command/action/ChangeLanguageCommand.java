package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeLanguageCommand.class);

    private static final String LANGUAGE = "language";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String referer = request.getHeader("referer");
        String backURI = null;
        try {
            backURI = referer.substring(referer.indexOf("controller"));
        } catch (StringIndexOutOfBoundsException e) {
            backURI = ResourceManager.getProperty("command.home");
        }
        String language = request.getParameter(LANGUAGE).toUpperCase();
        if (language == null) {
            logger.log(Level.WARN, "Parameter language is null!");
        } else {
            request.getSession().setAttribute(LANGUAGE, language);
        }
        return new CommandResult(backURI, true);
    }
}
