package com.katsubo.finaltask.command.menu.impl;

import com.katsubo.finaltask.command.ResourceManager;
import com.katsubo.finaltask.command.menu.Menu;
import com.katsubo.finaltask.command.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class AdministratorMenu implements Menu {
    private List<MenuItem> items;

    public AdministratorMenu() {
        items = new ArrayList<>();
        items.add(new MenuItem("my_conferences", ResourceManager.getProperty("command.userEvents")));
        items.add(new MenuItem("profile", ResourceManager.getProperty("command.profile")));
        items.add(new MenuItem("add_conference", ResourceManager.getProperty("command.addEvent")));
        items.add(new MenuItem("menu.allUsers", ResourceManager.getProperty("command.allUsers")));//todo create allUsers command

    }

    @Override
    public List<MenuItem> getMenuItems() {
        return items;
    }
}
