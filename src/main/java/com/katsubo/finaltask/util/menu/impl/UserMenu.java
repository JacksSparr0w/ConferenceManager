package com.katsubo.finaltask.util.menu.impl;

import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.util.menu.Menu;
import com.katsubo.finaltask.util.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class UserMenu implements Menu {
    private List<MenuItem> items;

    public UserMenu() {
        items = new ArrayList<>();
        items.add(new MenuItem("menu.my_conferences", ResourceManager.getProperty("command.userEvents")));
        items.add(new MenuItem("menu.profile", ResourceManager.getProperty("command.profile")));
        items.add(new MenuItem("menu.add_conference", ResourceManager.getProperty("command.addEventPage")));
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return items;
    }
}
