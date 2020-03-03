package com.vitkovskaya.finalProject.command.cleaner;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaning;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.CleaningServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ChangeCleaningStatusCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        CleaningServiceImpl cleaningService = new CleaningServiceImpl();
        List<Cleaning> cleaningList;
        String start = content.getRequestParameter("start");
        String start2 = content.getRequestParameter("pageStart");
        logger.log(Level.DEBUG, start + " START FOR PAGINATION ");
        logger.log(Level.DEBUG, start2 + " START FOR PAGINATION ");
        User user = (User) content.getSessionAttribute(ConstantName.PARAMETER_USER);
        Long cleaningId = Long.valueOf(content.getRequestParameter(ConstantName.PARAMETER_CLEANING_ID));
        Boolean availableStatus = Boolean.valueOf(content.getRequestParameter(ConstantName.PARAMETER_CLEANING_STATUS));
        try {
            if (cleaningService.changeCleaningStatus(cleaningId, availableStatus)) {
                cleaningList = cleaningService.findCleaningByCleanerId(user.getUserId());
                if (!cleaningList.isEmpty()) {
                    content.addSessionAttribute("start", start2);
                    content.addSessionAttribute(ConstantName.ATTRIBUTE_CLEANING_LIST, cleaningList);
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CLEANINGS));
                    router.setType(RouteType.FORWARD);
                } else {
                    content.addRequestAttribute(ConstantName.ATTRIBUTE_SHOW_CLEANING_ERROR,
                            MessageManager.getProperty(ConstantName.MESSAGE_SHOW_CLEANING_ERROR));
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CLEANINGS));
                    router.setType(RouteType.FORWARD);
                }
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_CLEANING_STATUS_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_CLEANING_STATUS_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CLEANINGS));
                router.setType(RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Error while executing command", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            router.setType(RouteType.FORWARD);
        }

        return router;
    }
}
