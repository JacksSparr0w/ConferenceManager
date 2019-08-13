package com.katsubo.finaltask.entity;

import com.katsubo.finaltask.entity.enums.Role;

import java.util.Map;

/**
 * The type Users on event.
 */
public class UsersOnEvent {
    private Integer eventId;
    private Map<Integer, Role> users;

    /**
     * Gets event id.
     *
     * @return the event id
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * Sets event id.
     *
     * @param eventId the event id
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public Map<Integer, Role> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(Map<Integer, Role> users) {
        this.users = users;
    }
}
