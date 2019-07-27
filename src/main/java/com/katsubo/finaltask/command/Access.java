package com.katsubo.finaltask.command;

import com.katsubo.finaltask.command.factory.CommandType;
import com.katsubo.finaltask.entity.UserDto;
import com.katsubo.finaltask.entity.enums.Permission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Access {
    private static final Logger logger = LogManager.getLogger(Access.class);
    private static final Map<Integer, List<CommandType>> commandsAccess = new HashMap<>();
    private static final List<CommandType> commonRules = new ArrayList<>();
    private static final List<CommandType> adminRules = new ArrayList<>();
    private static final List<CommandType> userRules = new ArrayList<>();
    private static final List<CommandType> guestRules = new ArrayList<>();

    private static Access instance;

    /***
     * about access
     * there's three layer or access
     * 1 - administrator
     * 2 - user
     * 0 - guest
     * so, allocate command according their access layer
     */

    private Access(){

        commandsAccess.put(Permission.ADMINISTRATOR.getFieldCode(), adminRules);
        commandsAccess.put(Permission.USER.getFieldCode(), userRules);
        //for guest
        commandsAccess.put(0, guestRules);

        setCommonRules();
        setGuestRules();
        setUserRules();
        setAdminRules();
    }

    public static Access getInstance() {
        if (instance == null) {
            instance = new Access();
        }
        return instance;
    }

    private void setCommonRules(){
        commonRules.add(CommandType.HOME_PAGE);
        commonRules.add(CommandType.CHANGE_LANGUAGE);
        commonRules.add(CommandType.GET_EVENTS);
    }

    private void setGuestRules(){
        guestRules.addAll(commonRules);

        guestRules.add(CommandType.LOGIN);
        guestRules.add(CommandType.LOGOUT);
        guestRules.add(CommandType.REGISTER);
    }

    private void setUserRules(){
        userRules.addAll(commonRules);

        userRules.add(CommandType.ADD_EVENT);
        userRules.add(CommandType.EDIT_USER);
        userRules.add(CommandType.EDIT_USER_INFO);
        userRules.add(CommandType.PROFILE);
        userRules.add(CommandType.REGISTER_TO_EVENT);
        userRules.add(CommandType.SIGN_OUT_FOR_EVENT);
        userRules.add(CommandType.USER_EVENTS);
        userRules.add(CommandType.GET_EVENTS);
    }

    private void setAdminRules(){
        adminRules.addAll(userRules);

        adminRules.add(CommandType.EDIT_EVENT);
        adminRules.add(CommandType.REMOVE_EVENT);
        adminRules.add(CommandType.GET_USERS_ON_EVENT);
    }





    public boolean can(String command, HttpServletRequest request) throws IllegalArgumentException{
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        Integer accessLayer;
        UserDto user = (UserDto) request.getSession().getAttribute(Constances.USER.getFieldName());
        if (user != null){
            accessLayer = user.getPermission().getFieldCode();
        } else {
            accessLayer = 0;
        }

        List<CommandType> rules = commandsAccess.get(accessLayer);

        return rules.contains(commandType);
    }
}
