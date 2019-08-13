package com.katsubo.finaltask.command.factory;

import java.util.stream.Stream;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * Login command type.
     */
    LOGIN("login"),
    /**
     * Login page command type.
     */
    LOGIN_PAGE("login_page"),
    /**
     * Register page command type.
     */
    REGISTER_PAGE("register_page"),
    /**
     * Logout command type.
     */
    LOGOUT("logout"),
    /**
     * Register command type.
     */
    REGISTER("register"),
    /**
     * Add event command type.
     */
    ADD_EVENT("add_event"),
    /**
     * Add event page command type.
     */
    ADD_EVENT_PAGE("add_event_page"),
    /**
     * Remove event command type.
     */
    REMOVE_EVENT("remove_event"),
    /**
     * Edit event command type.
     */
    EDIT_EVENT("edit_event"),
    /**
     * Edit event page command type.
     */
    EDIT_EVENT_PAGE("edit_event_page"),
    /**
     * Profile command type.
     */
    PROFILE("profile"),
    /**
     * Edit user command type.
     */
    EDIT_USER("edit_user"),
    /**
     * Edit user info command type.
     */
    EDIT_USER_INFO("edit_user_info"),
    /**
     * Edit user photo command type.
     */
    EDIT_USER_PHOTO("edit_user_photo"),
    /**
     * Delete user command type.
     */
    DELETE_USER("delete_user"),
    /**
     * Register to event command type.
     */
    REGISTER_TO_EVENT("register_to_event"),
    /**
     * Leave event command type.
     */
    LEAVE_EVENT("leave_event"),
    /**
     * User events command type.
     */
    USER_EVENTS("user_events"),
    /**
     * Get users on event command type.
     */
    GET_USERS_ON_EVENT("get_users_on_event"),
    /**
     * Home page command type.
     */
    HOME_PAGE("home_page"),
    /**
     * Change language command type.
     */
    CHANGE_LANGUAGE("change_language"),
    /**
     * All users command type.
     */
    ALL_USERS("all_users"),
    /**
     * Start page command type.
     */
    START_PAGE("start_page"),
    /**
     * All events command type.
     */
    ALL_EVENTS("all_events");

    private String fieldName;

    CommandType(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Of command type.
     *
     * @param command the command
     * @return the command type
     */
    public static CommandType of(String command) {
        return Stream.of(CommandType.values())
                .filter(c -> c.fieldName.equalsIgnoreCase(command))
                .findFirst().orElse(HOME_PAGE);
    }
}
