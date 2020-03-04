package com.vitkovskaya.finalProject.command.order;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaning;
import com.vitkovskaya.finalProject.entity.CleaningItem;
import com.vitkovskaya.finalProject.service.CleaningListAction;
import com.vitkovskaya.finalProject.service.serviceImpl.CleaningServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClearOrderListCommand implements Command {
    /**
     * Remove all cleaning from the order list.
     * Sets total sum value 0.
     * Returns router to the cleanings catalog.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        List<CleaningItem> cleaningList = new ArrayList<>();
        BigDecimal totalSum = new BigDecimal(ConstantName.ZERO_VALUE);
        content.addSessionAttribute(ConstantName.ATTRIBUTE_TOTAL_ORDER_SUM, totalSum);
        content.addSessionAttribute(ConstantName.ATTRIBUTE_ORDER_LIST, cleaningList);
        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_GO_TO_CLEANING_LIST));
        router.setType(RouteType.FORWARD);
        return router;
    }
}
