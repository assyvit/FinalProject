package com.vitkovskaya.finalProject.command.client;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.OrderStatus;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.OrderServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelOrderCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets the order id from the request.
     * Checks, if the order status is new. Only new order can be cancelled by user.
     * Otherwise message that this action is impossible is sent to the user.
     * Returns router to the same page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see OrderServiceImpl#changeOrderStatus(long, OrderStatus)
     */
    @Override
    public Router execute(RequestContent content) {
        String start = content.getRequestParameter(ConstantName.PARAMETER_PAGE_START);
        String page = (String) content.getSessionAttribute(ConstantName.ATTRIBUTE_PAGE_PATH);
        OrderServiceImpl orderService = new OrderServiceImpl();
        Router router = new Router();
    //    User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        Long oderId = Long.valueOf(content.getRequestParameter(ConstantName.PARAMETER_ORDER_ID));
        try {
            if (orderService.changeOrderStatus(oderId, OrderStatus.CANCELLED)) {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                router.setPagePath(page);
            } else {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_ORDER_CANCEL_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_ORDER_CANCEL_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_ORDERS));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while cancelling order", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
