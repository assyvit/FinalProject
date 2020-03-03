package com.vitkovskaya.finalProject.command.cleaner;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaner;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.CleanerServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import com.vitkovskaya.finalProject.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class EditCleanerProfileCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(RequestContent content) {
        DataValidator validator = new DataValidator();
        Map<String, String> userParameters = new HashMap<>();
        Router router = new Router();
        CleanerServiceImpl cleanerService = new CleanerServiceImpl();
        User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        Long userId = user.getUserId();
        String firstName = content.getRequestParameter(ConstantName.PARAMETER_FIRST_NAME);
        String lastName = content.getRequestParameter(ConstantName.PARAMETER_LAST_NAME);
        String address = content.getRequestParameter(ConstantName.PARAMETER_ADDRESS);
        String telephoneNumber = content.getRequestParameter(ConstantName.PARAMETER_TELEPHONE_NUMBER);
        userParameters.put(ConstantName.PARAMETER_FIRST_NAME, firstName);
        userParameters.put(ConstantName.PARAMETER_LAST_NAME, lastName);
        userParameters.put(ConstantName.PARAMETER_ADDRESS, address);
        userParameters.put(ConstantName.PARAMETER_TELEPHONE_NUMBER, telephoneNumber);
        Cleaner cleaner = new Cleaner(userId, firstName, lastName, address, telephoneNumber);
        try {
            validator.validateUserUpdateDate(userParameters);

            if (!userParameters.containsValue(ConstantName.ATTRIBUTE_EMPTY_VALUE)) {
                if (cleanerService.updateCleaner(cleaner)) {
                    content.addSessionAttribute(ConstantName.ATTRIBUTE_USER_PROFILE, cleaner);
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_PROFILE));
                    router.setType(RouteType.REDIRECT);
                } else {
                    content.addRequestAttribute(ConstantName.ATTRIBUTE_EDIT_PROFILE_ERROR,
                            MessageManager.getProperty(ConstantName.MESSAGE_EDIT_PROFILE_ERROR));
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_PROFILE));
                    router.setType(RouteType.FORWARD);
                }
            } else {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_USER_PROFILE, cleaner);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_VALIDATED_MAP, userParameters);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_EDIT_PROFILE_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_INPUT_DATA));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_PROFILE));
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
