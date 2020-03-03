package com.vitkovskaya.finalProject.command.order;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.CleaningItem;
import com.vitkovskaya.finalProject.util.ConfigurationManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClearOrderListCommand implements Command {
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        List<CleaningItem> cleaningList = new ArrayList<>();
        BigDecimal totalSum = new BigDecimal(ConstantName.ZERO_VALUE);
        content.addSessionAttribute(ConstantName.ATTRIBUTE_CART_SUM, totalSum);
        content.addSessionAttribute(ConstantName.ATTRIBUTE_ORDER_LIST, cleaningList);
        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_GO_TO_CLEANING_LIST));
        router.setType(RouteType.FORWARD);
        return router;
    }
}
