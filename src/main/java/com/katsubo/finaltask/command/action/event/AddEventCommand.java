package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.ResourceManager;
import com.katsubo.finaltask.command.Constances;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Address;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.enums.Theme;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.EventServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddEventCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddEventCommand.class);
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final String DESCRIPTION = "description";
    private static final String THEME = "theme";
    private static final String CAPACITY = "capacity";
    private static final String NAME = "name";
    private static final String DATE = "date";
    private static final String DONE = "done";
    private static final String ERROR_ADD_EVENT = "error_add_event";
    private static final String INVALID_FIELD = "invalid_field";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String BUILDING = "building";
    private static final String PICTURE = "picture";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(NAME, request.getParameter(NAME));
        parameters.put(DESCRIPTION, request.getParameter(DESCRIPTION));
        parameters.put(THEME, request.getParameter(THEME));
        parameters.put(DATE, request.getParameter(DATE));
        parameters.put(COUNTRY, request.getParameter(COUNTRY));
        parameters.put(CITY, request.getParameter(CITY));
        parameters.put(STREET, request.getParameter(STREET));
        parameters.put(BUILDING, request.getParameter(BUILDING));
        parameters.put(CAPACITY, request.getParameter(CAPACITY));

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                logger.log(Level.WARN, ERROR_ADD_EVENT + ": " + INVALID_FIELD + " - " + entry.getKey());
                request.setAttribute(INVALID_FIELD, entry.getKey());
                return failure(ERROR_ADD_EVENT, request);
            }
        }

        Event event = new Event();
        event.setName(parameters.get(NAME));
        event.setDescription(parameters.get(DESCRIPTION));

        try {
            Part part = request.getPart(PICTURE);
            if (part != null && part.getSize() > 0){
                String fileName = DigestUtils.md2Hex(parameters.get(NAME)) + "." + "jpg";
                String path = getPath();
                File file = new File(path + ResourceManager.getProperty("path.eventImageDirectory") + fileName);
                if (ImageIO.write(ImageIO.read(part.getInputStream()), "jpg", file)) {
                    event.setPictureLink(fileName);
                } else {
                    return failure(ERROR_ADD_EVENT, request);
                }
            }
        } catch (IOException | ServletException | NullPointerException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(ERROR_ADD_EVENT, request);
        }

        event.setTheme(Theme.valueOf(parameters.get(THEME).toUpperCase()));
        Date date;
        try {
            date = format.parse(parameters.get(DATE));
        } catch (ParseException pe) {
            logger.log(Level.INFO, ERROR_ADD_EVENT);
            return failure(ERROR_ADD_EVENT, request);
        }
        event.setDate(date);

        event.setAddress(new Address(
                parameters.get(COUNTRY),
                parameters.get(CITY),
                parameters.get(STREET),
                Integer.valueOf(parameters.get(BUILDING))
        ));

        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        if (user != null){
            event.setAuthor_id(user.getUserId());
        }else{
            logger.log(Level.INFO, ERROR_ADD_EVENT);
            return failure(ERROR_ADD_EVENT, request);
        }
        event.setCapacity(Integer.valueOf(parameters.get(CAPACITY)));

        try {
            add(event);
        } catch (DaoException | ServiceException e) {
            logger.log(Level.INFO, ERROR_ADD_EVENT);
            return failure(ERROR_ADD_EVENT, request);
        }

        request.setAttribute(DONE, true);
        request.setAttribute("event_id", event.getId());
        return new CommandResult(ResourceManager.getProperty("command.registerToEvent"));
        //request.setAttribute(Constances.EVENT.getFieldName(), event);
        //request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.addEvent"));
        //return new CommandResult(ResourceManager.getProperty("command.home"));
    }

    private String getPath() {
        String path = getClass().getClassLoader().getResource("").getPath();
        String[] pathArr = path.split("/WEB-INF/classes/");
        return pathArr[0];
    }

    private void add(Event event) throws DaoException, ServiceException {
        EventService service = new EventServiceImpl();
        service.save(event);
    }


    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(error, true);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.addEvent"));
        return new CommandResult(ResourceManager.getProperty("command.home"));
    }

}
