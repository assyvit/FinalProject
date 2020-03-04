package com.vitkovskaya.finalProject.command.cleaning;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaning;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.CleaningServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

public class GoCleaningProfileCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        String start = content.getRequestParameter(ConstantName.ATTRIBUTE_START);
        CleaningServiceImpl cleaningService = new CleaningServiceImpl();
        Long cleaningId = Long.valueOf(content.getRequestParameter(ConstantName.PARAMETER_CLEANING_ID));
        try {
            Optional<Cleaning> cleaningOptional = cleaningService.findCleaningById(cleaningId);
            if (cleaningOptional.isPresent()) {
                Cleaning cleaning = cleaningOptional.get();
                content.addSessionAttribute(ConstantName.ATTRIBUTE_CLEANING_ID, cleaningId);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_CLEANING, cleaning);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANING_PROFILE));
            } else {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_CLEANING_EDIT_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_CLEANING_SHOW_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CLEANINGS));
            }
        } catch (ServiceException e) {
            logger.error("Error while executing command", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
