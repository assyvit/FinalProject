package com.vitkovskaya.finalProject.command.common;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.email.SendEmail;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.UserServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import com.vitkovskaya.finalProject.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class AddCleanerCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets login, password, password conformation, firstName, lastName, address and telephone number
     * values from the request.
     * Validates this values, if input data is not valid, returns router to the same page with message
     * about incorrect input data.
     * Checks, if the user with this login already exists, returns router to the same page with message
     * about login already exists.
     * Checks, if the password and password confirmation are equals, returns router to the same page with message
     * about password equality
     * Otherwise, register new cleaner (creates entity and updates database),
     * sets sessions attributes for current user and
     * returns router to the client cabinet page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the admin has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#validateUserInputDate(Map)
     * @see UserServiceImpl#checkUserLogin(String)
     * @see UserServiceImpl#registerCleaner(Map)
     */
    @Override
    public Router execute(RequestContent content) {
        SendEmail email = new SendEmail();
        Router router = new Router();
        DataValidator validator = new DataValidator();
        Map<String, String> userParameters = new HashMap<>();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        Optional<User> optionalUser;
        String login = content.getRequestParameter(ConstantName.PARAMETER_LOGIN).trim();
        String password = content.getRequestParameter(ConstantName.PARAMETER_PASSWORD).trim();
        String passwordConfirmation = content.getRequestParameter(ConstantName.PARAMETER_PASSWORD_CONFIRMATION).trim();
        String firstName = content.getRequestParameter(ConstantName.PARAMETER_FIRST_NAME).trim();
        String lastName = content.getRequestParameter(ConstantName.PARAMETER_LAST_NAME).trim();
        String address = content.getRequestParameter(ConstantName.PARAMETER_ADDRESS).trim();
        String telephoneNumber = content.getRequestParameter(ConstantName.PARAMETER_TELEPHONE_NUMBER).trim();
        userParameters.put(ConstantName.PARAMETER_LOGIN, login);
        userParameters.put(ConstantName.PARAMETER_PASSWORD, password);
        userParameters.put(ConstantName.PARAMETER_PASSWORD_CONFIRMATION, passwordConfirmation);
        userParameters.put(ConstantName.PARAMETER_FIRST_NAME, firstName);
        userParameters.put(ConstantName.PARAMETER_LAST_NAME, lastName);
        userParameters.put(ConstantName.PARAMETER_ADDRESS, address);
        userParameters.put(ConstantName.PARAMETER_TELEPHONE_NUMBER, telephoneNumber);
        try {
            validator.validateUserInputDate(userParameters);
            if (!userParameters.containsValue(ConstantName.ATTRIBUTE_EMPTY_VALUE) && !userParameters.containsValue(null)
                    && !userServiceImpl.checkUserLogin(login)) {
                optionalUser = userServiceImpl.registerCleaner(userParameters);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    content.addSessionAttribute(ConstantName.ATTRIBUTE_USER, user);
                    // should be email sendTo -   user.getLogin() instead of ConstantName.REAL_EMAIL_FOR_TEST
                    user.getLogin();
                             email.send(ConstantName.REAL_EMAIL_FOR_TEST,
                                     MessageManager.getProperty(ConstantName.SUBJECT_SUCCESSFUL_REGISTRATION),
                                     MessageManager.getProperty(ConstantName.EMAIL_SUCCESSFUL_REGISTRATION));
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CABINET));
                    router.setType(RouteType.REDIRECT);
                } else {
                    content.addRequestAttribute(ConstantName.ATTRIBUTE_REGISTRATION_ERROR,
                            MessageManager.getProperty(ConstantName.MESSAGE_REGISTRATION_ERROR));
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ADD_CLEANER));
                }
            } else {
                if (userServiceImpl.checkUserLogin(login)) {
                    userParameters.put(ConstantName.PARAMETER_LOGIN, ConstantName.ATTRIBUTE_EMPTY_VALUE);
                    content.addRequestAttribute(ConstantName.ATTRIBUTE_LOGIN_NOT_UNIQ,
                            MessageManager.getProperty(ConstantName.MESSAGE_NOT_UNIQ_LOGIN_ERROR));
                }
                userParameters.put(ConstantName.PARAMETER_PASSWORD, ConstantName.ATTRIBUTE_EMPTY_VALUE);
                userParameters.put(ConstantName.PARAMETER_PASSWORD_CONFIRMATION, ConstantName.ATTRIBUTE_EMPTY_VALUE);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_VALIDATED_MAP, userParameters);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_REGISTRATION_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_INPUT_DATA));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ADD_CLEANER));
            }
        } catch (ServiceException e) {
            logger.error("Error while adding cleaner to database", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
