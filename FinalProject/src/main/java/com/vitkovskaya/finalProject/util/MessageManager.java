package com.vitkovskaya.finalProject.util;


import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Message manager. Provide get access to resource bundle that contain messages
 * to user.
  */
public class MessageManager {
    private final static String FILE_NAME = "messages";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_NAME);
    // класс извлекает информацию из файла messages.properties
    private MessageManager() { }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
//
//    public static void setLocale(String language) {
//        resourceBundle = ResourceBundle.getBundle("resources", new Locale(language));
//    }