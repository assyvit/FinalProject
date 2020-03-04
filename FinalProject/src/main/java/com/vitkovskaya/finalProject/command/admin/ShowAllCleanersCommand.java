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

public class  ShowAllCleanersCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    /**
     * Gets all cleaners from the database,
     * sets the session attribute to show them and
     * returns router to the show cleaners page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see CleanerServiceImpl#findAll()
     */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        CleanerServiceImpl cleanerService = new CleanerServiceImpl();
        List<Cleaner> cleanerList;
        try {
            cleanerList = cleanerService.findAll();
            if (!cleanerList.isEmpty()) {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_CLEANER_LIST, cleanerList);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_SHOW_CLEANERS));
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_SHOW_CLEANERS_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_SHOW_CLEANER_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_SHOW_CLEANERS));
            }
        } catch (ServiceException e) {
            logger.error("Error while getting all cleaners", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
