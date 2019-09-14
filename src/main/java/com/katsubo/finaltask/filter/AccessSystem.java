package com.katsubo.finaltask.filter;

import com.katsubo.finaltask.command.factory.CommandType;
import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.entity.Rule;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.service.PermissionService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.PermissionServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.EnumSet;

import static com.katsubo.finaltask.command.factory.CommandType.*;

public class AccessSystem {
    private static final Logger logger = LogManager.getLogger(AccessSystem.class);
    private static EnumSet<CommandType> commands;

    public static void updateRules(UserDto userDto) {
        commands.clear();
        if (userDto == null) {
            makeRulesForGuest();
            return;
        } else {
            addMinimalRules();
        }
        try {
            PermissionService service = new PermissionServiceImpl();
            Permission permission = service.read(userDto.getPermissionId());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            makeRulesForGuest();
            return;
        }

        //TODO modify all events

        Arrays.stream(Rule.values())
                .forEach(n -> commands.addAll(n.getCommands()));

    }

    public static boolean checkAccess(CommandType commandType) {
        return checkAccess(commandType.name());
    }

    public static boolean checkAccess(String command) {
        return commands.contains(CommandType.of(command));
    }



    private static void addMinimalRules() {
        commands = EnumSet.of(LOGOUT, ADD_EVENT_PAGE, ADD_EVENT, EDIT_USER,
                EDIT_USER_INFO, PROFILE, REGISTER_TO_EVENT, LEAVE_EVENT,
                USER_EVENTS, EDIT_USER_PHOTO, EDIT_EVENT, EDIT_EVENT_PAGE,
                REMOVE_EVENT);

        commands.addAll(getCommonRules());
    }

    private static void makeRulesForGuest() {
        commands = EnumSet.of(LOGIN_PAGE, LOGIN, REGISTER, REGISTER_PAGE);
        commands.addAll(getCommonRules());
    }

    private static EnumSet<CommandType> getCommonRules() {
        return EnumSet.of(HOME_PAGE, CHANGE_LANGUAGE, START_PAGE, ALL_EVENTS);
    }
}
