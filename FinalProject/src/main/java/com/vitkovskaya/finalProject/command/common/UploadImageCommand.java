package com.vitkovskaya.finalProject.command.common;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.UserServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UploadImageCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(RequestContent content) {
        UserServiceImpl userService = new UserServiceImpl();
        Router router = new Router();
        User user = (User) content.getSessionAttribute(ConstantName.PARAMETER_USER);
        Long id = user.getUserId();
        String fileName = (String) content.getRequestAttribute(ConstantName.PARAMETER_FILE_NAME);
        String filePath = new StringBuilder().append(ConstantName.PARAMETER_UPLOAD_DIRECTORY).append(fileName).toString();
        try {
            if (userService.setUserAvatar(id, filePath)) {
                router.setType(RouteType.FORWARD);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_CABINET));
                content.addRequestAttribute("upload_result", " upload successfully ");
            } else {
                content.addRequestAttribute("warning", "LOAD FAIL");
                //content.addRequestAttribute(ConstantName.ATTRIBUTE_REGISTRATION_ERROR, ConstantName.MESSAGE_INCORRECT_INPUT_DATA);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_PROFILE));
                router.setType(RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Error while saving avatar path", e); // FIXME: 18.02.2020
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
