package com.katsubo.finaltask.command.factory;

public enum CommandType {
    LOGIN("login"),
    LOGOUT("logout"),
    REGISTER("register"),
    ADD_EVENT("add_event"),
    REMOVE_EVENT("remove_event"),
    EDIT_EVENT("edit_event"),
    PROFILE("profile"),
    EDIT_USER("edit_user"),
    EDIT_USER_INFO("edit_user_info"),
    SIGN_UP_FOR_EVENT("sign_up_for_event"),
    SIGN_OUT_FOR_EVENT("sign_out_for_event"),
    GET_EVENTS("get_events"),
    GET_USERS_ON_EVENT("get_users_on_event"),
    HOME_PAGE("home_page");

    private String fieldName;

    CommandType(String fieldName) {
        this.fieldName = fieldName;
    }
}
