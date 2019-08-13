package com.katsubo.finaltask.util;

import java.util.ResourceBundle;

/**
 * ResourceManager to get values from resource bundle
 */
public class ResourceManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources");

    private ResourceManager() {
    }

    /**
     * @param key to find in recource bindle
     * @return value that find by key
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
