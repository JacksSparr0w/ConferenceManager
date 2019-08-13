package com.katsubo.finaltask.util.menu;

/**
 * Class represents the menu item
 */
public class MenuItem {
    private String name;
    private String path;

    /**
     * @param name of the item
     * @param path path specify the item
     */
    public MenuItem(String name, String path) {
        this.name = name;
        this.path = path;
    }

    /**
     * @return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * @return path of the item
     */
    public String getPath() {
        return path;
    }
}
