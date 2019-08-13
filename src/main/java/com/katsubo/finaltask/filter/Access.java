package com.katsubo.finaltask.filter;

import com.katsubo.finaltask.util.Constances;
import com.katsubo.finaltask.command.factory.CommandType;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.enums.Permission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.katsubo.finaltask.command.factory.CommandType.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * The type Access.
 */
public class Access {
    private static final Logger logger = LogManager.getLogger(Access.class);
    private static final Map<Integer, Set<CommandType>> commandsAccess = new HashMap<>();
    private static EnumSet<CommandType> common;
    private static EnumSet<CommandType> guest;
    private static EnumSet<CommandType> user;
    private static EnumSet<CommandType> admin;

    private static Access instance;

    /***
     * about access
     * there's three layer or access
     * 1 - administrator
     * 2 - user
     * 0 - guest
     * so, allocate command according their access layer
     */

    private Access() {
        setCommonRules();
        setGuestRules();
        setUserRules();
        setAdminRules();

        commandsAccess.put(Permission.ADMINISTRATOR.getFieldCode(), admin);
        commandsAccess.put(Permission.USER.getFieldCode(), user);
        commandsAccess.put(0, guest);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Access getInstance() {
        if (instance == null) {
            instance = new Access();
        }
        return instance;
    }

    private void setCommonRules() {
        common = EnumSet.of(HOME_PAGE, CHANGE_LANGUAGE, START_PAGE, ALL_EVENTS);
    }

    private void setGuestRules() {
        guest = EnumSet.of(LOGIN_PAGE, LOGIN, REGISTER, REGISTER_PAGE);
        guest.addAll(common);
    }

    private void setUserRules() {
        user = EnumSet.of(LOGOUT, ADD_EVENT_PAGE, ADD_EVENT, EDIT_USER, EDIT_USER_INFO,
                PROFILE, REGISTER_TO_EVENT, LEAVE_EVENT, USER_EVENTS, EDIT_USER_PHOTO,
                EDIT_EVENT, EDIT_EVENT_PAGE, REMOVE_EVENT);

        user.addAll(common);
    }

    private void setAdminRules() {
        admin = EnumSet.of(GET_USERS_ON_EVENT, ALL_USERS, DELETE_USER);

        admin.addAll(user);
    }


    /**
     * Get list of commands that can do user and check if command contains in list
     *
     * @param command the command
     * @param request the request
     * @return the boolean
     * @throws IllegalArgumentException the illegal argument exception
     */
    public boolean can(String command, HttpServletRequest request) throws IllegalArgumentException {
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        Integer accessLayer;
        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        if (user != null) {
            accessLayer = user.getPermission().getFieldCode();
        } else {
            accessLayer = 0;
        }

        Set<CommandType> rules = commandsAccess.get(accessLayer);

        return rules.contains(commandType);
    }
}
