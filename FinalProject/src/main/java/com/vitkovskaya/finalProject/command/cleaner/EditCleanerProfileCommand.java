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
    /**
     * Gets first name, last name, address, telephone number.
     * Validates this values, if input data is not valid,
     * returns router to the same page with message about invalid values.
     * Otherwise, edits cleaner and returns router to the same page.
     * Not allowed to change cleaner id.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#validateUserUpdateDate(Map)
     * @see CleanerServiceImpl#updateCleaner(Cleaner) 
     */
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
                }
            } else {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_USER_PROFILE, cleaner);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_VALIDATED_MAP, userParameters);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_EDIT_PROFILE_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_INPUT_DATA));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_PROFILE));
            }
        } catch (ServiceException e) {
            logger.error("Error while editing cleaner", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
