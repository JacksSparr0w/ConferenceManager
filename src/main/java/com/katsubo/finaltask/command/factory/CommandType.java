package com.katsubo.finaltask.command.factory;

public enum CommandType {
    LOGIN("login"),
    LOGIN_PAGE("login_page"),
    REGISTER_PAGE("register_page"),
    LOGOUT("logout"),
    REGISTER("register"),
    ADD_EVENT("add_event"),
    ADD_EVENT_PAGE("add_event_page"),
    REMOVE_EVENT("remove_event"),
    EDIT_EVENT("edit_event"),
    PROFILE("profile"),
    EDIT_USER("edit_user"),
    EDIT_USER_INFO("edit_user_info"),
    EDIT_USER_PHOTO("edit_user_photo"),
    REGISTER_TO_EVENT("register_to_event"),
    LEAVE_EVENT("leave_event"),
    USER_EVENTS("user_events"),
    GET_EVENTS("get_events"),
    GET_USERS_ON_EVENT("get_users_on_event"),
    HOME_PAGE("home_page"),
    CHANGE_LANGUAGE("change_language");

    private String fieldName;

    CommandType(String fieldName) {
        this.fieldName = fieldName;
    }
}
