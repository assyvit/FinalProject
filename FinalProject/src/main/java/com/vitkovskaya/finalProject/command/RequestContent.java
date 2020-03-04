package com.vitkovskaya.finalProject.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The class allows you to retrieve the necessary information from request, and
 * save it in instance.
 */

public class RequestContent {
    private final static Logger logger = LogManager.getLogger();
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private boolean invalidateSession;

    public boolean isInvalidateSession() {
        return invalidateSession;
    }

    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }

    public RequestContent(HttpServletRequest request) {
        this.requestAttributes = new HashMap<>();
        this.requestParameters = new HashMap<>();
        this.sessionAttributes = new HashMap<>();
        extractValues(request);
             }

    /**
     * Methods for extracting information from request
     */
    public void extractValues(HttpServletRequest request) {
        logger.log(Level.INFO, "Extract values from request");
        if (request.getParameterNames() != null) {
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String parameterName = paramNames.nextElement();
                String[] values = request.getParameterValues(parameterName);
                requestParameters.put(parameterName, values);
            }
        }
        if (request.getAttributeNames() != null) {
            Enumeration<String> attrNames = request.getAttributeNames();
            while (attrNames.hasMoreElements()) {
                String attributeName = attrNames.nextElement();
                requestAttributes.put(attributeName, request.getAttribute(attributeName));
            }
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            Enumeration<String> sessionAttrNames = request.getSession().getAttributeNames();
            while (sessionAttrNames.hasMoreElements()) {
                String sessionAttributeName = sessionAttrNames.nextElement();
                sessionAttributes.put(sessionAttributeName, session.getAttribute(sessionAttributeName));
            }
        }
    }

    /**
     * Method for inserting attributes from Maps
     */
    public void insertValues(HttpServletRequest request) {
        logger.log(Level.INFO, "Insert values into request");
        if (invalidateSession) {
            request.getSession().invalidate();
        }
        Iterator iterator = requestAttributes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> pair = (Map.Entry) iterator.next();
            request.setAttribute(pair.getKey(), pair.getValue());
        }
        iterator = sessionAttributes.entrySet().iterator();
        HttpSession session = request.getSession(false);
        if (session != null) {
            while (iterator.hasNext()) {
                Map.Entry<String, Object> pair = (Map.Entry) iterator.next();
                session.setAttribute(pair.getKey(), pair.getValue());
            }
        }
    }

    public String getRequestParameter(String parameterName) {
        String[] parameterValues = requestParameters.getOrDefault(parameterName, null);
        return parameterValues != null ? parameterValues[0] : null;
    }

    public Object getRequestAttribute(String attributeName) {
        return requestAttributes.get(attributeName);
    }

    public Object getSessionAttribute(String attributeName) {
        return sessionAttributes.get(attributeName);
    }

    public void addRequestAttribute(String key, Object value) {
        requestAttributes.put(key, value);
    }

    public void addSessionAttribute(String attributeName, Object attributeValue) {
        sessionAttributes.put(attributeName, attributeValue);
    }

   }
