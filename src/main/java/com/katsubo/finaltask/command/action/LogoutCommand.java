package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ConfigurationManager;
import com.katsubo.finaltask.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.katsubo.finaltask.command.Constances.*;

public class LogoutCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id");
        String userRole = (String) session.getAttribute("role");
        logger.log(Level.INFO, "user was above: id:" + userId + " role:" + userRole);
        session.removeAttribute(ID.getFieldName());
        session.removeAttribute(ROLE.getFieldName());
        session.removeAttribute(USER.getFieldName());
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }
}
