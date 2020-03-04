package com.vitkovskaya.finalProject.command.common;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageCommand implements Command {

    private final static String LANGUAGE_ATTRIBUTE = "language";
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets locale value from the request and
     * sets this value as session attribute                                                                                          (if the value is not null),
     * returns router to the same page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        String local = content.getRequestParameter(ConstantName.PARAMETER_LANGUAGE);
        String pagePath = (String) content.getSessionAttribute(ConstantName.ATTRIBUTE_PAGE_PATH);
        content.addSessionAttribute(LANGUAGE_ATTRIBUTE, local);
        content.addSessionAttribute(ConstantName.ATTR_LOCALE, local);
        if (pagePath == null) {
            pagePath = ConfigurationManager.getProperty(ConstantName.JSP_MAIN);
        }
        router.setPagePath(pagePath);
        return router;
    }
}
