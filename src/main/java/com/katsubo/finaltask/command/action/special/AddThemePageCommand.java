package com.katsubo.finaltask.command.action.special;

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
import java.util.ArrayList;
import java.util.List;

public class AddThemePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddThemePageCommand.class);
    private static final String EXIST_THEMES = "existThemes";

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
        List<Value> existThemes = new ArrayList<>();
        try {
            existThemes = readExistThemes();
        } catch (ServiceException e){
            logger.log(Level.WARN, "Cant readByID existing themes");
        }
        request.setAttribute(EXIST_THEMES, existThemes);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.addTheme"));
        String page = ResourceManager.getProperty("page.main");
        return new CommandResult(page);
    }

    private List<Value> readExistThemes() throws ServiceException {
        ThemeService service = new ThemeServiceImpl();
        return service.findAll();
    }
}
