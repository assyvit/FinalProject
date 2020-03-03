package com.vitkovskaya.finalProject.command.order;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.email.SendEmail;
import com.vitkovskaya.finalProject.entity.*;
import com.vitkovskaya.finalProject.service.CleaningListAction;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.OrderServiceImpl;
import com.vitkovskaya.finalProject.service.serviceImpl.UserServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import com.vitkovskaya.finalProject.validator.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;

public class OrderCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets payment type and pick up time values from request,
     * checks, if the cart is empty (if customer removed cleanings in a new browser tab),
     * returns router to the shopping cart page.
     * <p>
     * Places new order (updates database) and clears the shopping cart,
     * returns router to the confirmed order page with success message and
     * message that order will be auto cancelled if it is not picked up at time.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see OrderServiceImpl#
     * @see OrderServiceImpl#
     */

    @Override
    public Router execute(RequestContent content) {
        DataValidator validator = new DataValidator();
        SendEmail sendEmail = new SendEmail();
        OrderServiceImpl orderService = new OrderServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        Router router = new Router();
        CleaningListAction action = new CleaningListAction();
        Map<String, String> inputData = new HashMap<>();
        List<CleaningItem> cleaningList = (List<CleaningItem>)
                content.getSessionAttribute(ConstantName.ATTRIBUTE_ORDER_LIST);
        User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        String date = content.getRequestParameter(ConstantName.EXECUTING_DATE).trim();
        String paymentType = content.getRequestParameter(ConstantName.PARAMETER_PAYMENT_TYPE).trim();
        String comment = content.getRequestParameter(ConstantName.PARAMETER_ORDER_COMMENT).trim();
        BigDecimal orderSum = new BigDecimal((content.getSessionAttribute(ConstantName.ATTRIBUTE_CART_SUM).toString()));
        inputData.put(ConstantName.PARAMETER_PAYMENT_TYPE, paymentType);
        inputData.put(ConstantName.EXECUTING_DATE, date);
        inputData.put(ConstantName.PARAMETER_ORDER_COMMENT, comment);
        try {
            validator.validateOrderInputData(inputData);
            logger.log(Level.DEBUG, !inputData.containsValue("") + "-----> FIRST VALIDATION");
            if (!inputData.containsValue(ConstantName.ATTRIBUTE_EMPTY_VALUE)) {
                Map<Long, Long> resultMap = orderService.createOrder(user, date, paymentType, comment, cleaningList);
                if (!resultMap.isEmpty()
                        && orderSum.compareTo(BigDecimal.ZERO) != ConstantName.ZERO_VALUE) {
                    Set<Long> cleanersId = action.getCleanersId(cleaningList);
                    for (Long id : cleanersId) {
                        Optional<User> cleanerOptional = userService.findById(id);
                        if (cleanerOptional.isPresent()) {
                            String sentTo = cleanerOptional.get().getLogin();
//                            sendEmail.send(sentTo,
//                                    MessageManager.getProperty(ConstantName.SUBJECT_NEW_ORDER),
//                                    MessageManager.getProperty(ConstantName.EMAIL_NEW_ORDER));

                            sendEmail.send("nanna.vit@gmail.com",
                                    MessageManager.getProperty(ConstantName.SUBJECT_NEW_ORDER),
                                    MessageManager.getProperty(ConstantName.EMAIL_NEW_ORDER));
                        } else {
                            content.addRequestAttribute(ConstantName.ATTRIBUTE_ORDER_ERROR,
                                    ConstantName.MESSAGE_ORDER_ERROR);
                            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_CABINET));
                            router.setType(RouteType.FORWARD);
                        }
                    }
                    orderSum = new BigDecimal(ConstantName.ZERO_VALUE);
                    cleaningList = new ArrayList<>();
                    content.addSessionAttribute(ConstantName.ATTRIBUTE_CART_SUM, orderSum);
                    content.addSessionAttribute(ConstantName.ATTRIBUTE_ORDER_LIST, cleaningList);
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_CABINET));
                    router.setType(RouteType.REDIRECT);
                } else {
                    content.addRequestAttribute(ConstantName.ATTRIBUTE_ORDER_ERROR,
                            ConstantName.MESSAGE_ORDER_ERROR);
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_CABINET));
                    router.setType(RouteType.FORWARD); // FIXME: 20.02.2020
                }
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_VALIDATED_MAP, inputData);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CHECK_OUT));
                router.setType(RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Error while validating data", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            router.setType(RouteType.REDIRECT);
        }
        return router;
    }
}
