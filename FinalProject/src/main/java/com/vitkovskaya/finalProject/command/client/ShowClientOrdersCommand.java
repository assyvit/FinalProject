package com.vitkovskaya.finalProject.command.client;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Order;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.OrderServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowClientOrdersCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Shows all client orders.
     * Sets the session attribute to show orders and
     * returns router to the orders page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see OrderServiceImpl#findAllClientOrders(Long)
      */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        OrderServiceImpl orderService = new OrderServiceImpl();
        List<Order> orderList;
        User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        String start = content.getRequestParameter(ConstantName.PARAMETER_PAGE_START);
        Long id = user.getUserId();
        try {
            orderList = orderService.findAllClientOrders(id);
            if (!orderList.isEmpty()) {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_CLIENT_ORDER_LIST, orderList);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_ORDERS));
            } else {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_SHOW_ORDERS_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_SHOW_ORDER_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_ORDERS));
            }
        } catch (ServiceException e) {
            logger.error("Error while getting all client orders", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;


    }
}
