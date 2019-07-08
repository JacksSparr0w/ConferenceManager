package com.katsubo.final_task.entity;

import com.katsubo.final_task.entity.enums.Permission;

public class User extends Entity {
    private String login;
    private String password;
    private Permission permission;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

}
