package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.entity.Address;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.enums.Theme;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.EventServiceImpl;
import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.validate.EventValidator;
import com.katsubo.finaltask.validate.Validator;
import com.katsubo.finaltask.validate.ValidatorException;
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
    private static final String ERROR_ADD_EVENT = "event.add.fail";
    private static final String INVALID_FIELD = "invalid_field";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String BUILDING = "building";
    private static final String PICTURE = "picture";
    public static final String ERROR = "error";
    public static final String ADD_EVENT_SUCCESS = "event.add.success";

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
            if (part != null && part.getSize() > 0) {
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
            logger.log(Level.WARN, ERROR_ADD_EVENT);
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
        if (user != null) {
            event.setAuthor_id(user.getUserId());
        } else {
            logger.log(Level.WARN, ERROR_ADD_EVENT);
            return failure(ERROR_ADD_EVENT, request);
        }


        try {
            Integer capacity = Integer.valueOf(parameters.get(CAPACITY));
            event.setCapacity(capacity);
            if (valid(event)) {
                add(event);
            }
        } catch (ValidatorException e){
            logger.log(Level.WARN, e.getMessage());
            return failure(ERROR_ADD_EVENT, request);
        } catch (NumberFormatException | ServiceException e) {
            logger.log(Level.WARN, ERROR_ADD_EVENT);
            return failure(ERROR_ADD_EVENT, request);
        }

        request.setAttribute(DONE, ADD_EVENT_SUCCESS);
        return new CommandResult(ResourceManager.getProperty("command.allEvents"));
    }

    private boolean valid(Event event) throws ValidatorException {
        Validator validator = new EventValidator();
        String error =  validator.isValid(event);
        if (error != null){
            throw new ValidatorException(error);
        } else {
            return true;
        }
    }

    private String getPath() {
        String path = getClass().getClassLoader().getResource("").getPath();
        String[] pathArr = path.split("/WEB-INF/classes/");
        return pathArr[0];
    }

    private void add(Event event) throws ServiceException {
        EventService service = new EventServiceImpl();
        service.save(event);
    }


    private CommandResult failure(String error, HttpServletRequest request) {
        request.setAttribute(ERROR, error);
        request.setAttribute(Constances.INCLUDE.getFieldName(), ResourceManager.getProperty("page.addEvent"));
        return new CommandResult(ResourceManager.getProperty("command.home"));
    }

}
