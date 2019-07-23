package com.katsubo.finaltask.command.factory;

import com.katsubo.finaltask.command.action.*;

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
                //
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
            case SIGN_UP_FOR_EVENT:
                //
                break;
            case SIGN_OUT_FOR_EVENT:
                //
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
                default:
                    throw new IllegalArgumentException("Invalid command " + command);
        }
        return result;
    }
}

