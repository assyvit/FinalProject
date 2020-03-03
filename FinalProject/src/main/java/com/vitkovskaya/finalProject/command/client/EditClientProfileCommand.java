package com.vitkovskaya.finalProject.command.client;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.ClientServiceImpl;
import com.vitkovskaya.finalProject.service.serviceImpl.UserServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class EditClientProfileCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets first name, last name, address, telephone number.
     * Validates this values, if input data is not valid,
     * returns router to the same page with message about invalid values.
     * Otherwise, edits cleaner and returns router to the same page with success message.
     * <p>
     * Not allowed to change client role, login and id.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#validateUserInputDate(Map)
     * @see ClientServiceImpl#updateClient(Client)
     */
    @Override
    public Router execute(RequestContent content) {
        DataValidator validator = new DataValidator();
        UserServiceImpl userService = new UserServiceImpl();
        Map<String, String> userParameters = new HashMap<>();
        Router router = new Router();
        ClientServiceImpl clientService = new ClientServiceImpl();
        User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        Client client = (Client) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER_PROFILE);

//        userParameters.put(ConstantName.PARAMETER_FIRST_NAME, client.getFirstName());
//        userParameters.put(ConstantName.PARAMETER_LAST_NAME, client.getLastName());
//        userParameters.put(ConstantName.PARAMETER_ADDRESS, client.getAddress());
//        userParameters.put(ConstantName.PARAMETER_TELEPHONE_NUMBER, client.getTelephoneNumber());

        String firstName = content.getRequestParameter(ConstantName.PARAMETER_FIRST_NAME);
        String lastName = content.getRequestParameter(ConstantName.PARAMETER_LAST_NAME);
        String address = content.getRequestParameter(ConstantName.PARAMETER_ADDRESS);
        String telephoneNumber = content.getRequestParameter(ConstantName.PARAMETER_TELEPHONE_NUMBER);

        userParameters.put(ConstantName.PARAMETER_FIRST_NAME, firstName);
        userParameters.put(ConstantName.PARAMETER_LAST_NAME, lastName);
        userParameters.put(ConstantName.PARAMETER_ADDRESS, address);
        userParameters.put(ConstantName.PARAMETER_TELEPHONE_NUMBER, telephoneNumber);
        try {
            validator.validateUserInputDate(userParameters);
            if (!userParameters.containsValue(ConstantName.ATTRIBUTE_EMPTY_VALUE) &&
                    !userParameters.containsValue(null)) {
                client.setFirstName(userParameters.get(ConstantName.PARAMETER_FIRST_NAME));
                client.setLastName(userParameters.get(ConstantName.PARAMETER_LAST_NAME));
                client.setAddress(userParameters.get(ConstantName.PARAMETER_ADDRESS));
                client.setTelephoneNumber(userParameters.get(ConstantName.PARAMETER_TELEPHONE_NUMBER));
                if (clientService.updateClient(client)) {
                    content.addSessionAttribute(ConstantName.ATTRIBUTE_USER_PROFILE, client);
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_PROFILE));
                    router.setType(RouteType.REDIRECT);
                } else {
                    content.addRequestAttribute(ConstantName.ATTRIBUTE_EDIT_PROFILE_ERROR,
                            ConstantName.MESSAGE_REGISTRATION_ERROR);
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_CABINET));
                    router.setType(RouteType.REDIRECT);
                }
            } else {
                client.setFirstName(userParameters.get(ConstantName.PARAMETER_FIRST_NAME));
                client.setLastName(userParameters.get(ConstantName.PARAMETER_LAST_NAME));
                client.setAddress(userParameters.get(ConstantName.PARAMETER_ADDRESS));
                client.setTelephoneNumber(userParameters.get(ConstantName.PARAMETER_TELEPHONE_NUMBER));
                content.addSessionAttribute(ConstantName.ATTRIBUTE_USER_PROFILE, client);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_VALIDATED_MAP, userParameters);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_EDIT_PROFILE_ERROR,
                        ConstantName.MESSAGE_INCORRECT_INPUT_DATA);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_PROFILE));
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

