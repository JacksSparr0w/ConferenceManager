package com.katsubo.finaltask.entity;

import com.katsubo.finaltask.entity.enums.Role;

import java.util.Map;

public class UsersOnEvent {
    private Integer eventId;
    private Map<Integer, Role> users;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Map<Integer, Role> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, Role> users) {
        this.users = users;
    }
}
