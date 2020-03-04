package com.vitkovskaya.finalProject.command.common;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.UserServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import com.vitkovskaya.finalProject.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangePasswordCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    /**
     * Gets user password which he wants to change from the request.
     * Validates input value, if input data is not valid, returns router to the same page
     * with message about invalid input data.
     * Otherwise, edits user value (updates database) and returns router to the same page with success message.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see UserServiceImpl#changePassword(User, String)
     */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        UserServiceImpl userService = new UserServiceImpl();
        DataValidator validator = new DataValidator();
        User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        String currentPassword = content.getRequestParameter(ConstantName.PARAMETER_CURRENT_PASSWORD);
        String newPassword = content.getRequestParameter(ConstantName.PARAMETER_NEW_PASSWORD);
        String confirmPassword = content.getRequestParameter(ConstantName.PARAMETER_PASSWORD_CONFIRMATION);
        try {
            if (userService.findUserByLoginAndPassword(user.getLogin(), currentPassword)) {
                if (validator.validateChangedPassword(newPassword, confirmPassword)) {
                    if (userService.changePassword(user, newPassword)) {
                        content.addRequestAttribute(ConstantName.ATTRIBUTE_PASSWORD_CHANGE_SUCCESS,
                                MessageManager.getProperty(ConstantName.MESSAGE_PASSWORD_CHANGE_SUCCESS));
                        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_PASSWORD_CHANGE));
                    } else {
                        content.addRequestAttribute(ConstantName.ATTRIBUTE_PASSWORD_CHANGE_ERROR,
                                MessageManager.getProperty(  ConstantName.MESSAGE_PASSWORD_CHANGE_ERROR));
                        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_PASSWORD_CHANGE));
                    }
                } else {
                    content.addRequestAttribute(ConstantName.ATTRIBUTE_PASSWORD_MATCH,
                            MessageManager.getProperty(ConstantName.MESSAGE_PASSWORDS_NOT_EQUAL));
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_PASSWORD_CHANGE));
                }
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_CURRENT_PASSWORD_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_CURRENT_PASSWORD_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_PASSWORD_CHANGE));
            }
        } catch (ServiceException e) {
            logger.error("Error while changing password command", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
