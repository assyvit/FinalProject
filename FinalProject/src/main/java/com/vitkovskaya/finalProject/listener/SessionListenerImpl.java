package com.vitkovskaya.finalProject.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionListenerImpl implements HttpSessionAttributeListener {
    private final static Logger logger = LogManager.getLogger();
    private final static String ADD = "add: ";
    private final static String REPLACE = "replace: ";
    private final static String SEPARATE = " : ";

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        StringBuilder builder = new StringBuilder();
        builder.append(ADD).append(event.getClass().getSimpleName()).append(SEPARATE).append(event.getName()).
                append(SEPARATE).append(event.getValue());
        logger.log(Level.DEBUG, builder.toString());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        StringBuilder builder = new StringBuilder();
        builder.append(REPLACE).append(event.getClass().getSimpleName()).append(SEPARATE).append(event.getName()).
                append(SEPARATE).append(event.getValue());
        logger.log(Level.DEBUG, builder.toString());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }
}