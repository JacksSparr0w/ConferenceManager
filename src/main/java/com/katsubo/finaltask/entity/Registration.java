package com.katsubo.finaltask.entity;

import com.katsubo.finaltask.entity.enums.Role;

import java.util.Objects;

public class Registration extends Entity {
    private Integer userId;
    private Integer eventId;
    private Role role;

    public Registration() {
    }

    public Registration(Integer userId, Integer eventId, Role role) {
        this.userId = userId;
        this.eventId = eventId;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Registration that = (Registration) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(eventId, that.eventId) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, eventId, role);
    }

    @Override
    public String toString() {
        return "Registration{" +
                "userId=" + userId +
                ", eventId=" + eventId +
                ", role=" + role +
                '}';
    }
}
