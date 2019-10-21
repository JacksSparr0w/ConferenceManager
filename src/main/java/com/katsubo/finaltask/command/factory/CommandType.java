package com.katsubo.finaltask.command.factory;

import java.util.stream.Stream;
import static com.katsubo.finaltask.command.factory.RequestType.*;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * Login command type.
     */
    LOGIN("login", POST),
    /**
     * Login page command type.
     */
    LOGIN_PAGE("login_page", GET),
    /**
     * Register page command type.
     */
    REGISTER_PAGE("register_page", GET),
    /**
     * Logout command type.
     */
    LOGOUT("logout", GET),
    /**
     * Register command type.
     */
    REGISTER("register", POST),
    /**
     * Add event command type.
     */
    ADD_EVENT("add_event", POST),
    /**
     * Add event page command type.
     */
    ADD_EVENT_PAGE("add_event_page", GET),
    /**
     * Remove event command type.
     */
    REMOVE_EVENT("remove_event", GET),
    /**
     * Edit event command type.
     */
    EDIT_EVENT("edit_event", POST),
    /**
     * Edit event page command type.
     */
    EDIT_EVENT_PAGE("edit_event_page", GET),
    /**
     * Profile command type.
     */
    PROFILE("profile", GET),
    /**
     * Edit user command type.
     */
    EDIT_USER("edit_user", POST),
    /**
     * Edit user info command type.
     */
    EDIT_USER_INFO("edit_user_info", POST),
    /**
     * Edit user photo command type.
     */
    EDIT_USER_PHOTO("edit_user_photo", POST),
    /**
     * Delete user command type.
     */
    DELETE_USER("delete_user", POST),
    /**
     * Register to event command type.
     */
    REGISTER_TO_EVENT("register_to_event", POST),
    /**
     * Leave event command type.
     */
    LEAVE_EVENT("leave_event", GET),
    /**
     * User events command type.
     */
    USER_EVENTS("user_events", GET),
    /**
     * Get users on event command type.
     */
    GET_USERS_ON_EVENT("get_users_on_event", GET),
    /**
     * Home page command type.
     */
    HOME_PAGE("home_page", GET),
    /**
     * Change language command type.
     */
    CHANGE_LANGUAGE("change_language", GET),
    /**
     * All users command type.
     */
    ALL_USERS("all_users", GET),
    /**
     * Start page command type.
     */
    START_PAGE("start_page", GET),
    /**
     * All events command type.
     */
    ALL_EVENTS("all_events", GET),

    /**
     * Add role command type.
     */
    ADD_ROLE("add_role", POST),
    /**
     * Add theme command type.
     */
    ADD_THEME("add_theme", POST),
    /**
     * Add permission command type.
     */
    ADD_PERMISSION("add_permission", POST),
    /**
     * Add role command type.
     */
    ADD_ROLE_PAGE("add_role_page", GET),
    /**
     * Add theme command type.
     */
    ADD_THEME_PAGE("add_theme_page", GET),
    /**
     * Add permission command type.
     */
    ADD_PERMISSION_PAGE("add_permission_page", GET),
    /**
     * Change user permission command type.
     */
    CHANGE_USER_PERMISSION("change_user_permission", POST),

    /**
     * Modify all events command type.
     */
    MODIFY_ANY_EVENT("modify_any_event", GET),

    /**
     * Delete any event command type.
     */
    DELETE_ANY_EVENT("delete_any_event", GET);

    private String fieldName;

    private RequestType requestType;

    CommandType(String fieldName, RequestType requestType) {
        this.fieldName = fieldName;
        this.requestType = requestType;
    }

    /**
     * Get request type request type.
     *
     * @return the request type
     */
    public RequestType getRequestType(){
        return requestType;
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
