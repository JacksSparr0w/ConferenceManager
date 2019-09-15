package com.katsubo.finaltask.entity;

import com.katsubo.finaltask.command.factory.CommandType;

import java.util.EnumSet;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The enum Rule.
 */
public enum Rule {
    /**
     * Modify all events rules.
     */
    MODIFY_ANY_EVENT("modify_any_event", EnumSet.of(CommandType.MODIFY_ANY_EVENT, CommandType.DELETE_ANY_EVENT)),
    /**
     * Change user permission rules.
     */
    CHANGE_USER_PERMISSION("change_user_permission", EnumSet.of(CommandType.CHANGE_USER_PERMISSION)),
    /**
     * All users rules.
     */
    ALL_USERS("all_users", EnumSet.of(CommandType.ALL_USERS)),
    /**
     * Delete user rule.
     */
    DELETE_USER("delete_user", EnumSet.of(CommandType.DELETE_USER)),
    /**
     * Add theme rules.
     */
    ADD_THEME("add_theme", EnumSet.of(CommandType.ADD_THEME, CommandType.ADD_THEME_PAGE)),
    /**
     * Add role rules.
     */
    ADD_ROLE("add_role", EnumSet.of(CommandType.ADD_ROLE, CommandType.ADD_ROLE_PAGE)),
    /**
     * Add permission rules.
     */
    ADD_PERMISSION("add_permission", EnumSet.of(CommandType.ADD_PERMISSION, CommandType.ADD_PERMISSION_PAGE));

    private final String value;
    private final EnumSet<CommandType> commands;

    private Rule(String value, EnumSet<CommandType> commands) {
        this.value = value;
        this.commands = commands;
    }

    /**
     * Of optional.
     *
     * @param value the value
     * @return the optional
     */
    public static Optional<Rule> of(String value) {
        return Stream.of(Rule.values())
                .filter(c -> c.value.equalsIgnoreCase(value))
                .findFirst();
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets commands.
     *
     * @return the commands
     */
    public EnumSet<CommandType> getCommands() {
        return commands;
    }

}
