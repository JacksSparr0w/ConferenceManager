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

    static {
        items.put(ALL_USERS, new MenuItem("menu.allUsers", ResourceManager.getProperty("command.allUsers")));
        items.put(ADD_ROLE, new MenuItem("menu.addRole", ResourceManager.getProperty("command.addRolePage")));
        items.put(ADD_THEME, new MenuItem("menu.addTheme", ResourceManager.getProperty("command.addThemePage")));
        items.put(ADD_PERMISSION, new MenuItem("menu.addPermission", ResourceManager.getProperty("command.addPermissionPage")));

        basicItems.add(new MenuItem("menu.my_conferences", ResourceManager.getProperty("command.userEvents")));
        basicItems.add(new MenuItem("menu.profile", ResourceManager.getProperty("command.profile")));
        basicItems.add(new MenuItem("menu.add_conference", ResourceManager.getProperty("command.addEventPage")));
    }

    public static List<MenuItem> getMenuItems(Permission permission) {
        if (permission == null) return new ArrayList<>();
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.addAll(basicItems);
        menuItemList.addAll(
                permission.getRules().stream()
                        .filter(items::containsKey)
                        .map(items::get)
                        .collect(Collectors.toList())
        );
        return menuItemList;
    }
}
