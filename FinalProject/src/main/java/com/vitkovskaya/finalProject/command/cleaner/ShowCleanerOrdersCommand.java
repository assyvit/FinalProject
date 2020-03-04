package com.vitkovskaya.finalProject.command.cleaner;

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

public class ShowCleanerOrdersCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Shows all cleaner orders.
     * Sets the session attribute to show orders and
     * returns router to the orders page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see OrderServiceImpl#findAllCleanerOrders(Long)
     */
    @Override
    public Router execute(RequestContent content) {
        String start = content.getRequestParameter(ConstantName.PARAMETER_PAGE_START);
        Router router = new Router();
        OrderServiceImpl orderService = new OrderServiceImpl();
        List<Order> orderList;
        User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        Long id = user.getUserId();
        try {
            orderList = orderService.findAllCleanerOrders(id);
            if (!orderList.isEmpty()) {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_ORDERS_LIST, orderList);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_SHOW_CLEANER_ORDERS));
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_SHOW_ORDERS_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_SHOW_ORDER_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_ORDERS));
            }
        } catch (ServiceException e) {
            logger.error("Error while showing all cleaner orders", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
