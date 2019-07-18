package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ConfigurationManager;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.EventServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomeCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(HomeCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Event> events;
        try {
            events = readEvents();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (events == null || events.size() == 0) {
            logger.log(Level.INFO, "there is not events yet!");
            request.setAttribute("error", "There is not events yet!");
        } else {
            setAttributesToSession(events, request);
        }
        return new CommandResult(ConfigurationManager.getProperty("path.page.event"));
    }

    private List<Event> readEvents() throws ServiceException, DaoException {
        EventService service = new EventServiceImpl();
        List<Event> events = service.findAll();
        return events;
    }


    private void setAttributesToSession(List<Event> events, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("events", events);
    }
}
