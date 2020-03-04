package com.vitkovskaya.finalProject.command.cleaner;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaner;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.CleanerServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GoCleanerProfileCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        CleanerServiceImpl cleanerService = new CleanerServiceImpl();
        User user = (User) content.getSessionAttribute(ConstantName.PARAMETER_USER);
        Long cleanerId = user.getUserId();
        try {
            Optional<Cleaner> cleanerOptional = cleanerService.findCleanerById(cleanerId);
            if (cleanerOptional.isPresent()) {
                Cleaner cleaner = cleanerOptional.get();
                content.addSessionAttribute(ConstantName.ATTRIBUTE_USER_PROFILE, cleaner);
//                content.addRequestAttribute(ConstantName.ATTRIBUTE_VALIDATED_MAP, cleaner);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_PROFILE));
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_EDIT_PROFILE_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_PROFILE_SHOW_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CABINET));
            }
        } catch (ServiceException e) {
            logger.error("Error while executing command", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
