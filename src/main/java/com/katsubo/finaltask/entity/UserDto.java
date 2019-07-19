package com.katsubo.finaltask.entity;

import com.katsubo.finaltask.entity.enums.Permission;

import java.util.Objects;

public class UserDto {
    private Integer userId;
    private String login;
    private Permission permission;

    public UserDto() {

    }

    public UserDto(User user) {
        this.userId = user.getId();
        this.login = user.getLogin();
        this.permission = user.getPermission();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(login, userDto.login) &&
                permission == userDto.permission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, permission);
    }
}
