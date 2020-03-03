package com.vitkovskaya.finalProject.command.client;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Order;
import com.vitkovskaya.finalProject.entity.OrderStatus;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.OrderServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CancelOrderCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets the order id from the request.
     * Checks, if the order status is new. Onle new order can be cancelled by user.
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
        OrderServiceImpl orderService = new OrderServiceImpl();
        Router router = new Router();
        User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        Long oderId = Long.valueOf(content.getRequestParameter(ConstantName.PARAMETER_ORDER_ID));
        try {
            if (orderService.changeOrderStatus(oderId, OrderStatus.CANCELLED)) {
                List<Order> orderList = orderService.findAllClientOrders(user.getUserId());
                content.addSessionAttribute(ConstantName.ATTRIBUTE_CLIENT_ORDER_LIST, orderList);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_ORDERS));
                router.setType(RouteType.FORWARD);
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_ORDER_CANCEL_ERROR,
                        ConstantName.MESSAGE_ORDER_CANCEL_ERROR);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_ORDERS));
                router.setType(RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while cancelling order", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
