package com.vitkovskaya.finalProject.util;

import java.util.ResourceBundle;
/**
 * Configurator manager. Provide get access to resource bundle that contain some
 * path page.
 */

 public class ConfigurationManager {
    private final static String FILE_NAME = "config";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_NAME);
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
