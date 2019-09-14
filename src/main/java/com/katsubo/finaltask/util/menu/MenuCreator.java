package com.katsubo.finaltask.util.menu;

import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.entity.Rule;
import com.katsubo.finaltask.util.ResourceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.katsubo.finaltask.entity.Rule.*;

public class MenuCreator {
    private static final HashMap<Rule, MenuItem> items = new HashMap<>();
    private static final List<MenuItem> basicItems = new ArrayList<>();

    //todo fill menuItems
    static {
        items.put(ALL_USERS, new MenuItem("menu.all_users", "#"));
        items.put(ADD_ROLE, new MenuItem("menu.add_role", "#"));
        items.put(ADD_THEME, new MenuItem("menu.add_theme", "#"));
        items.put(ADD_PERMISSION, new MenuItem("menu.add_permission", "#"));

        basicItems.add(new MenuItem("menu.my_conferences", ResourceManager.getProperty("command.userEvents")));
        basicItems.add(new MenuItem("menu.profile", ResourceManager.getProperty("command.profile")));
        basicItems.add(new MenuItem("menu.add_conference", ResourceManager.getProperty("command.addEventPage")));
    }

    public static List<MenuItem> getMenuItems(Permission permission) {
        if (permission == null) return new ArrayList<>();
        List<MenuItem> menuItems = permission.getRules().stream()
                .map(items::get)
                .collect(Collectors.toList());
        menuItems.addAll(basicItems);
        return menuItems;
    }
}
