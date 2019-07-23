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
import java.util.List;

import static com.katsubo.finaltask.command.Constances.INCLUDE;

public class HomeCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(HomeCommand.class);
    private static final String THERE_NOT_EVENTS = "there_not_events";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Event> allEvents;
        try {
            allEvents = readEvents();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (allEvents == null || allEvents.size() == 0) {
            logger.log(Level.INFO, "there is not events yet!");
            request.setAttribute("error", THERE_NOT_EVENTS);
        } else {
            setAttributesToRequest(allEvents, request);
        }
        return new CommandResult(ConfigurationManager.getProperty("path.page.main"));
    }

    private List<Event> readEvents() throws ServiceException, DaoException {
        EventService service = new EventServiceImpl();
        List<Event> events = service.findAll();
        return events;
    }


    private void setAttributesToRequest(List<Event> events, HttpServletRequest request) {
        request.setAttribute(INCLUDE.getFieldName(), ConfigurationManager.getProperty("path.page.eventsShort"));
        request.setAttribute("events", events);
    }
}
