package com.vitkovskaya.finalProject.command.cleaner;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaning;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.CleaningServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowCleanerCleaningCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    /**
     * Shows all cleaner cleanings.
     * Sets the session attribute to show orders and
     * returns router to the orders page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see CleaningServiceImpl#findCleaningByCleanerId(long)
     */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        String start = content.getRequestParameter(ConstantName.PARAMETER_PAGE_START);
        User user = (User) content.getSessionAttribute(ConstantName.PARAMETER_USER);
        CleaningServiceImpl cleaningService = new CleaningServiceImpl();
        List<Cleaning> cleaningList;
        try {
            cleaningList = cleaningService.findCleaningByCleanerId(user.getUserId());
            if (!cleaningList.isEmpty()) {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_CLEANING_LIST, cleaningList);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CLEANINGS));
            } else {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_SHOW_CLEANING_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_SHOW_CLEANING_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CLEANINGS));
            }
        } catch (ServiceException e) {
            logger.error("Error while getting all cleaner cleanings", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
