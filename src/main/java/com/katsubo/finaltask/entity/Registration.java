package com.katsubo.finaltask.entity;

import java.util.Objects;

/**
 * The type Registration.
 */
public class Registration extends Entity {
    private Integer userId;
    private Integer eventId;
    private Value role;

    /**
     * Instantiates a new Registration.
     */
    public Registration() {
    }

    /**
     * Instantiates a new Registration.
     *
     * @param userId  the user id
     * @param eventId the event id
     * @param role    the role
     */
    public Registration(Integer userId, Integer eventId, Value role) {
        this.userId = userId;
        this.eventId = eventId;
        this.role = role;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
     * Gets role.
     *
     * @return the role
     */
    public Value getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Value role) {
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
