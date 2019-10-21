package com.katsubo.finaltask.filter;

import com.katsubo.finaltask.command.factory.CommandType;
import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.entity.Rule;
import com.katsubo.finaltask.entity.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;

import static com.katsubo.finaltask.command.factory.CommandType.*;

public class AccessSystem {
    private static final Logger logger = LogManager.getLogger(AccessSystem.class);
    private static EnumSet<CommandType> commands = EnumSet.noneOf(CommandType.class);

    public static void updateRules(UserDto userDto) {
        commands.clear();
        if (userDto == null) {
            makeRulesForGuest();
            return;
        } else {
            addMinimalRules();
        }

        Permission specialRules = userDto.getPermission();

        specialRules.getRules().stream()
                .map(Rule::getCommands)
                .forEach(commands::addAll);

    }

    public static boolean checkAccess(CommandType commandType) {
        return commands.contains(commandType);
    }

    public static boolean checkAccess(String command) {
        return checkAccess(CommandType.of(command));
    }


    private static void addMinimalRules() {
        commands = EnumSet.of(LOGOUT, ADD_EVENT_PAGE, ADD_EVENT, EDIT_USER,
                EDIT_USER_INFO, PROFILE, REGISTER_TO_EVENT, LEAVE_EVENT,
                USER_EVENTS, EDIT_USER_PHOTO, EDIT_EVENT, EDIT_EVENT_PAGE,
                REMOVE_EVENT, GET_USERS_ON_EVENT);

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
