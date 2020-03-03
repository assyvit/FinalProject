package com.vitkovskaya.finalProject.command.order;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.CleaningItem;
import com.vitkovskaya.finalProject.service.CleaningListAction;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;


public class RemoveFromOrderListCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets cleaning id from the request,
     * removes the cleaning from the shopping cart and
     * recalculates cart sum.
     * Returns router to the same page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see CleaningListAction#removeItem(List, long)
     */

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        CleaningListAction action = new CleaningListAction();
        List<CleaningItem> cleaningList = (List<CleaningItem>)
                content.getSessionAttribute(ConstantName.ATTRIBUTE_ORDER_LIST);
        Long itemId = Long.valueOf(content.getRequestParameter(ConstantName.ATTRIBUTE_CLEANING_ID));
        action.removeItem(cleaningList, itemId);
        BigDecimal totalSum = action.calculateTotalSum(cleaningList);
        content.addSessionAttribute(ConstantName.ATTRIBUTE_CART_SUM, totalSum);
        content.addSessionAttribute(ConstantName.ATTRIBUTE_ORDER_LIST, cleaningList);
        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_GO_TO_CLEANING_LIST));
        router.setType(RouteType.FORWARD);
        return router;
    }
}

