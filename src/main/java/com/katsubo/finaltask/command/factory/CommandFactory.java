package com.katsubo.finaltask.command.factory;

import com.katsubo.finaltask.command.action.*;
import com.katsubo.finaltask.command.action.authorization.*;
import com.katsubo.finaltask.command.action.event.*;
import com.katsubo.finaltask.command.action.special.*;
import com.katsubo.finaltask.command.action.useraction.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * The type Command factory.
 */
public class CommandFactory {
    private static Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    static {
        commands.put(CommandType.LOGIN, new LoginCommand());
        commands.put(CommandType.LOGOUT, new LogoutCommand());
        commands.put(CommandType.REGISTER, new RegisterCommand());
        commands.put(CommandType.ADD_EVENT, new AddEventCommand());
        commands.put(CommandType.REMOVE_EVENT, new RemoveEventCommand());
        commands.put(CommandType.EDIT_EVENT, new EditEventCommand());
        commands.put(CommandType.EDIT_EVENT_PAGE, new EditEventPageCommand());
        commands.put(CommandType.PROFILE, new ProfileCommand());
        commands.put(CommandType.EDIT_USER, new EditUserCommand());
        commands.put(CommandType.EDIT_USER_INFO, new EditUserInfoCommand());
        commands.put(CommandType.EDIT_USER_PHOTO, new EditUserPhotoCommand());
        commands.put(CommandType.DELETE_USER, new DeleteUserCommand());
        commands.put(CommandType.REGISTER_TO_EVENT, new RegisterToEventCommand());
        commands.put(CommandType.LEAVE_EVENT, new LeaveEventCommand());
        commands.put(CommandType.USER_EVENTS, new UserEventsCommand());
        commands.put(CommandType.HOME_PAGE, new HomePageCommand());
        commands.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(CommandType.LOGIN_PAGE, new LoginPageCommand());
        commands.put(CommandType.REGISTER_PAGE, new RegisterPageCommand());
        commands.put(CommandType.ADD_EVENT_PAGE, new AddEventPageCommand());
        commands.put(CommandType.ALL_USERS, new AllUsersCommand());
        commands.put(CommandType.START_PAGE, new HomePageCommand());
        commands.put(CommandType.ALL_EVENTS, new AllEventsCommand());
        //TODO create commands
        commands.put(CommandType.ADD_ROLE, new AddRoleCommand());
        commands.put(CommandType.ADD_ROLE_PAGE, new AddRolePageCommand());
        commands.put(CommandType.ADD_THEME, new AddThemeCommand());
        commands.put(CommandType.ADD_THEME_PAGE, new AddThemePageCommand());
        commands.put(CommandType.ADD_PERMISSION, new AddPermissionCommand());
        commands.put(CommandType.ADD_PERMISSION_PAGE, new AddPermissionPageCommand());
        commands.put(CommandType.CHANGE_USER_PERMISSION, null);

    }

    /**
     * Create command.
     *
     * @param command the command
     * @return the command
     */
    public static Command create(String command) {
        return commands.get(CommandType.of(command));
    }
}

