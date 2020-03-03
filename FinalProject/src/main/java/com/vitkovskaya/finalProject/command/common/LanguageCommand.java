package com.vitkovskaya.finalProject.command.common;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

/**
 * The {@code LanguageCommand} class
 * is a command to set locale.
 */
public class LanguageCommand implements Command {
    private final static String LANGUAGE_PARAMETER = "newLanguage";
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


        String local = content.getRequestParameter(LANGUAGE_PARAMETER);
        String pagePath = (String) content.getSessionAttribute("pagePath");
        logger.log(Level.DEBUG, pagePath);
        content.addSessionAttribute(LANGUAGE_ATTRIBUTE, local);
        if (pagePath == null) {
            pagePath = ConfigurationManager.getProperty(ConstantName.JSP_MAIN);
        }
        router.setPagePath(pagePath);
        router.setType(RouteType.FORWARD);
        return router;
    }
}
