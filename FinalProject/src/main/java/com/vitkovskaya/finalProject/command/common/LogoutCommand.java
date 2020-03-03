package com.vitkovskaya.finalProject.command.common;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.util.ConfigurationManager;

/**
 * The {@code LogoutCommand} class
 * is a command to log out user from the system.
 *
 */
public class LogoutCommand implements Command {
    /**
     * Invalidates user session.
     * Returns router to the main page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        content.setInvalidateSession(true);
        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_MAIN));
        router.setType(RouteType.REDIRECT);
        return router;
    }
}
