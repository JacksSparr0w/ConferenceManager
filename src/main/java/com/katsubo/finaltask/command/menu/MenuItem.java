package com.katsubo.finaltask.command.menu;

public class MenuItem {
    private String name;
    private String path;

    public MenuItem(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
