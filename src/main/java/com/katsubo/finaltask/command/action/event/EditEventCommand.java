package com.katsubo.finaltask.command.action.event;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.command.factory.CommandType;
import com.katsubo.finaltask.entity.Address;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.Value;
import com.katsubo.finaltask.filter.AccessSystem;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Edit event command.
 */
public class EditEventCommand implements Command {
    private static final String DURATION = "duration";
    private static final Logger logger = LogManager.getLogger(EditEventCommand.class);
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final String DESCRIPTION = "description";
    private static final String THEME = "theme";
    private static final String CAPACITY = "capacity";
    private static final String NAME = "name";
    private static final String DATE = "date";
    private static final String DONE = "done";
    private static final String EVENT_EDIT_FAIL = "event.edit.fail";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String BUILDING = "building";
    private static final String PICTURE = "picture";
    private static final String EVENT_ID = "eventId";
    private static final String ERROR_FIND_EVENT = "error_find_event";
    private static final String ERROR = "error";
    private static final String EVENT_EDIT_SUCCESS = "event.edit.success";
    private static final String INVALID_TYPE_OF_FILE = "invalid_type_of_file";
    private static final List<String> formats = new ArrayList<>();
    private static final String ACCESS_CLOSED = "access_closed";
    public static final int MILLIS = 1000;

    static {
        formats.add("jpg");
        formats.add("jpeg");
        formats.add("png");
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String eventIdString = request.getParameter(EVENT_ID);
        if (eventIdString == null) {
            logger.log(Level.WARN, ERROR_FIND_EVENT);
            return failure(ERROR_FIND_EVENT, request);
        }
        Event event = null;
        try {
            Integer eventId = Integer.valueOf(eventIdString);
            event = getEvent(eventId);
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, EVENT_EDIT_FAIL);
            return failure(ERROR_FIND_EVENT, request);
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(e.getMessage(), request);
        }

        if (!checkRules(event.getAuthor_id(), (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName()))) {
            logger.log(Level.WARN, "Access closed");
            return failure(ACCESS_CLOSED, request);
        }
        String name = request.getParameter(NAME);
        if (name != null && !name.isEmpty()) {
            event.setName(name);
        }

        String description = request.getParameter(DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            event.setDescription(description);
        }

        try {
            Part part = request.getPart(PICTURE);
            if (part.getSize() > 0) {
                String path = getPath();
                String format = part.getContentType().substring(part.getContentType().lastIndexOf('/') + 1);
                String fileName = DigestUtils.md2Hex(event.getName() + event.getDate() + "." + format);
                if (!formats.contains(format.toLowerCase())) {
                    logger.log(Level.WARN, INVALID_TYPE_OF_FILE);
                    return failure(INVALID_TYPE_OF_FILE, request);
                }
                File file = new File(path + ResourceManager.getProperty("path.eventImageDirectory") + fileName);
                if (ImageIO.write(ImageIO.read(part.getInputStream()), format, file)) {
                    event.setPictureLink(fileName);
                } else {
                    return failure(EVENT_EDIT_FAIL, request);
                }
            }
        } catch (IOException | ServletException | NullPointerException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(EVENT_EDIT_FAIL, request);
        }

        String theme = request.getParameter(THEME);
        if (theme != null && !theme.isEmpty()) {
            event.setTheme(new Value(Integer.valueOf(theme)));
        }

        Date date;
        try {
            date = format.parse(request.getParameter(DATE));
            event.setDate(date);
        } catch (ParseException pe) {
            logger.log(Level.INFO, EVENT_EDIT_FAIL);
            return failure(EVENT_EDIT_FAIL, request);
        }

        String country = request.getParameter(COUNTRY);
        String city = request.getParameter(CITY);
        String street = request.getParameter(STREET);
        String building = request.getParameter(BUILDING);
        if (country != null && !country.isEmpty() &&
                city != null && !city.isEmpty() &&
                street != null && !street.isEmpty() &&
                building != null && !building.isEmpty()) {
            event.setAddress(new Address(country, city, street, building));
        }

        String capacity = request.getParameter(CAPACITY);
        if (capacity != null && !capacity.isEmpty()) {
            try {
                event.setCapacity(Integer.valueOf(capacity));
            } catch (NumberFormatException e) {
                logger.log(Level.WARN, e.getMessage());
                return failure(EVENT_EDIT_FAIL, request);
            }
        }

        String duration = request.getParameter(DURATION);
        if (duration != null && !duration.isEmpty()) {
            try {
                event.setDuration(new Date(Integer.valueOf(duration) * MILLIS));
            } catch (NumberFormatException e) {
                logger.log(Level.WARN, e.getMessage());
                return failure(EVENT_EDIT_FAIL, request);
            }
        }

        try {
            if (valid(event)) {
                updateEvent(event);
            }
        } catch (ValidatorException | ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
            return failure(EVENT_EDIT_FAIL, request);
        }

        request.getSession().setAttribute(DONE, EVENT_EDIT_SUCCESS);
        return new CommandResult(ResourceManager.getProperty("command.allEvents"), true);
    }

    private boolean checkRules(Integer eventAuthor_id, UserDto userDto) {
        if (userDto == null) return false;
        if (userDto.getUserId().equals(eventAuthor_id)) return true;
        return AccessSystem.checkAccess(CommandType.MODIFY_ANY_EVENT);
    }

    private boolean valid(Event event) throws ValidatorException {
        Validator validator = new EventValidator();
        String error = validator.isValid(event);
        if (error != null) {
            throw new ValidatorException(error);
        } else {
            return true;
        }
    }

    private void updateEvent(Event event) throws ServiceException {
        EventService service = new EventServiceImpl();
        service.save(event);
    }

    private Event getEvent(Integer eventId) throws ServiceException {
        EventService service = new EventServiceImpl();
        Event event = service.findById(eventId);
        if (event == null) {
            throw new ServiceException(ERROR_FIND_EVENT);
        }
        return event;
    }

    private String getPath() {
        String path = getClass().getClassLoader().getResource("").getPath();
        String[] pathArr = path.split("/WEB-INF/classes/");
        return pathArr[0];
    }

    private CommandResult failure(String error, HttpServletRequest request) {
        request.getSession().setAttribute(ERROR, error);
        return new CommandResult(ResourceManager.getProperty("command.allEvents"), true);
    }

}
