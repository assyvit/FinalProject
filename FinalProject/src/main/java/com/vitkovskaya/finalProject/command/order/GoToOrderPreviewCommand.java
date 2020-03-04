package com.vitkovskaya.finalProject.command.order;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.service.serviceImpl.CleaningServiceImpl;
import com.vitkovskaya.finalProject.service.serviceImpl.OrderServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToOrderPreviewCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets a customer's order list from the session attribute,
     * defines its total price,
     * returns router to the order preview page.
     * If the list is empty, total sum isn't calculated.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see CleaningServiceImpl#findCleaningById(long)
     */

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        String start = content.getRequestParameter(ConstantName.PARAMETER_PAGE_START);
        content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_GO_TO_CLEANING_LIST));
        router.setType(RouteType.FORWARD);
        return router;
    }
}
