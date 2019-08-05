package com.katsubo.finaltask.util;

import java.util.ResourceBundle;

public class ResourceManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources");

    private ResourceManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
