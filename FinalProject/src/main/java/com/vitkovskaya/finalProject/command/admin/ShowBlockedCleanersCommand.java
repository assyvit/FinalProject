package com.vitkovskaya.finalProject.command.admin;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaner;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.CleanerServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class ShowBlockedCleanersCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets all blocked cleaners from the database,
     * sets the session attribute to show them and
     * returns router to the blocked cleaners page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see CleanerServiceImpl#findBlockedCleaners()
     */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        CleanerServiceImpl cleanerService = new CleanerServiceImpl();
        List<Cleaner> cleanerBlockedList;
        try {
            cleanerBlockedList = cleanerService.findBlockedCleaners();
            if (!cleanerBlockedList.isEmpty()) {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_CLEANER__BLOCKED_LIST, cleanerBlockedList);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_SHOW_BLOCKED_CLEANERS));
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_SHOW_CLEANERS_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_SHOW_CLEANER_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ADMIN_CABINET));
            }
        } catch (ServiceException e) {
            logger.error("Error while getting all blocked cleaners", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
