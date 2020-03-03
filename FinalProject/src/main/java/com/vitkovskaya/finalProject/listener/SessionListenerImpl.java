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

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        StringBuilder builder = new StringBuilder();
        builder.append("add: ").append(event.getClass().getSimpleName()).append(" : ").append(event.getName()).
                append(" : ").append(event.getValue());
        logger.log(Level.DEBUG, builder.toString());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        StringBuilder builder = new StringBuilder();
        builder.append("replace: ").append(event.getClass().getSimpleName()).append(" : ").append(event.getName()).
                append(" : ").append(event.getValue());
        logger.log(Level.DEBUG, builder.toString());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }
}