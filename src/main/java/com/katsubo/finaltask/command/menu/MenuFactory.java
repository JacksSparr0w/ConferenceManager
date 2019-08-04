package com.katsubo.finaltask.command.menu;

import com.katsubo.finaltask.command.menu.impl.AdministratorMenu;
import com.katsubo.finaltask.command.menu.impl.UserMenu;
import com.katsubo.finaltask.entity.enums.Permission;

public abstract class MenuFactory {
    public static Menu getMenu(Permission permission){
        Menu result = null;
        switch (permission){
            case USER:
                result = new UserMenu();
                break;
            case ADMINISTRATOR:
                result = new AdministratorMenu();
                break;
                default:
                    throw new UnsupportedOperationException("not supported permission");
        }
        return result;
    }
}
