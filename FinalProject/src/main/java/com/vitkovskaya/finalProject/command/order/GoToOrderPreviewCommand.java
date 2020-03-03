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
     * Gets a customer's shopping cart from the session attribute,
     * defines its total price,
     * returns router to the cart page.
     * If the cart is empty, the price isn't calculated.
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
        OrderServiceImpl orderService = new OrderServiceImpl();
        CleaningServiceImpl cleaningService = new CleaningServiceImpl();


        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_GO_TO_CLEANING_LIST));
        router.setType(RouteType.FORWARD);


        return router;
    }
}
