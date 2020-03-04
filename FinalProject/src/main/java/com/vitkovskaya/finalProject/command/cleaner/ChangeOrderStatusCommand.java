package com.vitkovskaya.finalProject.command.cleaner;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Order;
import com.vitkovskaya.finalProject.entity.OrderStatus;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.OrderServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ChangeOrderStatusCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    /**
     * Gets order status which cleaner wants to change from the request.
     * Edits order value (updates database) and returns router to the same page.
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
        OrderServiceImpl orderService = new OrderServiceImpl();
        Router router = new Router();
        User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        Long oderId = Long.valueOf(content.getRequestParameter(ConstantName.PARAMETER_ORDER_ID));
        OrderStatus orderStatus = OrderStatus.
                valueOf(content.getRequestParameter(ConstantName.PARAMETER_ORDER_STATUS).toUpperCase());
        try {
            if (orderService.changeOrderStatus(oderId, orderStatus)) {
                List<Order> orderList = orderService.findAllCleanerOrders(user.getUserId());
                content.addSessionAttribute(ConstantName.ATTRIBUTE_ORDERS_LIST, orderList);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_SHOW_CLEANER_ORDERS));
            } else {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_ORDER_CANCEL_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_ORDER_CANCEL_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_SHOW_CLEANER_ORDERS));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while changing order status", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;

    }
}
