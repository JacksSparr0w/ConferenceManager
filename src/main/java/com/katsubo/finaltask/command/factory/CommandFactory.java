package com.katsubo.finaltask.command.factory;

import com.katsubo.finaltask.command.action.event.DeleteEventCommand;
import com.katsubo.finaltask.command.action.event.EditEventCommand;
import com.katsubo.finaltask.command.action.DeleteUserCommand;
import com.katsubo.finaltask.command.action.HomePageCommand;
import com.katsubo.finaltask.command.action.*;
import com.katsubo.finaltask.command.action.authorization.*;
import com.katsubo.finaltask.command.action.event.*;
import com.katsubo.finaltask.command.action.useraction.*;

public class CommandFactory {

    public static Command create(String command){
        CommandType commandType = CommandType.valueOf(command.toUpperCase());

        Command result = null;
        switch (commandType){
            case LOGIN:
                result = new LoginCommand();
                break;
            case LOGOUT:
                result = new LogoutCommand();
                break;
            case REGISTER:
                result = new RegisterCommand();
                break;
            case ADD_EVENT:
                result = new AddEventCommand();
                break;
            case REMOVE_EVENT:
                result = new DeleteEventCommand();
                break;
            case EDIT_EVENT:
                result = new EditEventCommand();
                break;
            case EDIT_EVENT_PAGE:
                result = new EditEventPageCommand();
                break;
            case PROFILE:
                result = new ProfileCommand();
                break;
            case EDIT_USER:
                result = new EditUserCommand();
                break;
            case EDIT_USER_INFO:
                result = new EditUserInfoCommand();
                break;
            case EDIT_USER_PHOTO:
                result = new EditUserPhotoCommand();
                break;
            case DELETE_USER:
                result = new DeleteUserCommand();
                break;
            case REGISTER_TO_EVENT:
                result = new RegisterToEventCommand();
                break;
            case LEAVE_EVENT:
                result = new LeaveEventCommand();
                break;
            case USER_EVENTS:
                result = new UserEventsCommand();
                break;
            case GET_USERS_ON_EVENT:
                //
                break;
            case HOME_PAGE:
                result = new HomePageCommand();
                break;
            case CHANGE_LANGUAGE:
                result = new ChangeLanguageCommand();
                break;
            case LOGIN_PAGE:
                result = new LoginPageCommand();
                break;
            case REGISTER_PAGE:
                result = new RegisterPageCommand();
                break;
            case ADD_EVENT_PAGE:
                result = new AddEventPageCommand();
                break;
            case ALL_USERS:
                result = new AllUsersCommand();
                break;
            case START_PAGE:
                result = new HomePageCommand();
                break;
            case ALL_EVENTS:
                result = new AllEventsCommand();
                break;
                default:
                    throw new IllegalArgumentException("Invalid command " + command);
        }
        return result;
    }
}

