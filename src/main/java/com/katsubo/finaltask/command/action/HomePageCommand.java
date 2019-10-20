package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Home page command.
 */
public class HomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(HomePageCommand.class);
    private static final String LANGUAGE = "language";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String language = "en";
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equalsIgnoreCase(LANGUAGE)){
                    logger.log(Level.INFO, "cookies has been read");
                    language = cookie.getValue();
                }
            }
        }
        request.getSession().setAttribute(LANGUAGE, language);
        String page = ResourceManager.getProperty("page.main");
        return new CommandResult(page);
    }
}
