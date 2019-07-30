package com.katsubo.finaltask.command.factory;

import com.katsubo.finaltask.command.action.*;
import com.katsubo.finaltask.command.action.authorization.LoginCommand;
import com.katsubo.finaltask.command.action.authorization.LogoutCommand;
import com.katsubo.finaltask.command.action.authorization.RegisterCommand;
import com.katsubo.finaltask.command.action.useraction.*;

public class CommandFactory {

    public static ActionCommand create(String command){
        CommandType commandType = CommandType.valueOf(command.toUpperCase());

        ActionCommand result = null;
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
                //
                break;
            case EDIT_EVENT:
                //
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
            case REGISTER_TO_EVENT:
                result = new RegisterToEventCommand();
                break;
            case LEAVE_EVENT:
                result = new LeaveEventCommand();
                break;
            case USER_EVENTS:
                result = new UserEventsCommand();
                break;
            case GET_EVENTS:
                //
                break;
            case GET_USERS_ON_EVENT:
                //
                break;
            case HOME_PAGE:
                result = new HomeCommand();
                break;
            case CHANGE_LANGUAGE:
                result = new ChangeLanguageCommand();
                break;
                default:
                    throw new IllegalArgumentException("Invalid command " + command);
        }
        return result;
    }
}

