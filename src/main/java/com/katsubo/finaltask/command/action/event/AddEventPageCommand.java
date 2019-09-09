package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Value;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.ThemeService;
import com.katsubo.finaltask.service.impl.ThemeServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The type Add event page command.
 */
public class AddEventPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddEventPageCommand.class);
    public static final String ERROR = "error";
    private static final String SOME_WENT_WRONG = "some_went_wrong";
    private static final String THEMES = "themes";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<Value> themes = null;
        try {
            themes = getThemes();
        } catch (ServiceException e) {
            logger.log(Level.WARN, "Can't read themes");
            failure(request, SOME_WENT_WRONG);
        }
        request.setAttribute(THEMES, themes);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.addEvent"));
        String page = ResourceManager.getProperty("page.main");
        return new CommandResult(page);
    }

    private CommandResult failure(HttpServletRequest request, String error) {
        request.setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.home"), true);

    }

    private List<Value> getThemes() throws ServiceException {
        ThemeService service = new ThemeServiceImpl();
        return service.findAll();
    }
}
