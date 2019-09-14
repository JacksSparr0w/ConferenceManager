package com.katsubo.finaltask.entity;

import java.util.Objects;

/**
 * The type User dto.
 */
public class UserDto {
    private Integer userId;
    private String login;
    private Integer permissionId;

    /**
     * Instantiates a new User dto.
     */
    public UserDto() {

    }

    /**
     * Instantiates a new User dto.
     *
     * @param user the user
     */
    public UserDto(User user) {
        this.userId = user.getId();
        this.login = user.getLogin();
        this.permissionId = user.getPermissionId();
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
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets permissionId.
     *
     * @return the permissionId
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * Sets permissionId.
     *
     * @param permissionId the permissionId
     */
    public void setPermission(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(login, userDto.login) &&
                permissionId.equals(userDto.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, permissionId);
    }
}
