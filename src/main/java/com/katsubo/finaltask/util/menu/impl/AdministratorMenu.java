package com.katsubo.finaltask.util.menu.impl;

import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.util.menu.Menu;
import com.katsubo.finaltask.util.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Class provides menu for administrators
 */
public class AdministratorMenu implements Menu {
    private List<MenuItem> items;

    /**
     * Initialize administrator menu by menu items
     */
    public AdministratorMenu() {
        items = new ArrayList<>();
        items.add(new MenuItem("menu.my_conferences", ResourceManager.getProperty("command.userEvents")));
        items.add(new MenuItem("menu.profile", ResourceManager.getProperty("command.profile")));
        items.add(new MenuItem("menu.add_conference", ResourceManager.getProperty("command.addEventPage")));
        items.add(new MenuItem("menu.allUsers", ResourceManager.getProperty("command.allUsers")));

    }

    /**
     * @return list of menu items
     */
    @Override
    public List<MenuItem> getMenuItems() {
        return items;
    }
}
