package com.vitkovskaya.finalProject.command.common;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.email.SendEmail;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.UserServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class RecoverPasswordCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        UserServiceImpl userService = new UserServiceImpl();
        String login = content.getRequestParameter(ConstantName.PARAMETER_LOGIN).trim();
              try {
            if (userService.checkUserLogin(login)) {
                SendEmail sendEmail = new SendEmail();
                if (sendEmail.send(login, ConstantName.SUBJECT_PASSWORD_RECOVER, ConstantName.EMAIL_PASSWORD_RECOVER)) {
                    Optional<User> userOptional = userService.findByLogin(login);
                    if (userOptional.isPresent()) {
                        if (userService.changePassword(userOptional.get(), ConstantName.EMAIL_TEMPORARY_PASSWORD)) {
                            content.addRequestAttribute(ConstantName.ATTRIBUTE_PASSWORD_RECOVER_SUCCESS,
                                    ConstantName.MESSAGE_PASSWORD_RECOVER_SUCCESS);
                            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_LOGIN));
                            router.setType(RouteType.FORWARD);
                        } else {
                            setErrorMessage(content, router);
                        }
                    } else {
                        setErrorMessage(content, router);
                    }
                } else {
                    setErrorMessage(content, router);
                }
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_PASSWORD_RECOVER_NO_LOGIN,
                        ConstantName.MESSAGE_PASSWORD_RECOVER_NO_LOGIN);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_PASSWORD_RECOVER));
                router.setType(RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Error while recovering password", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            router.setType(RouteType.FORWARD);
        }


        return router;
    }

    private void setErrorMessage(RequestContent content, Router router) {
        content.addRequestAttribute(ConstantName.ATTRIBUTE_PASSWORD_RECOVER_ERROR,
                ConstantName.MESSAGE_PASSWORD_RECOVER_ERROR);
        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_PASSWORD_RECOVER));
        router.setType(RouteType.FORWARD);
    }
}
