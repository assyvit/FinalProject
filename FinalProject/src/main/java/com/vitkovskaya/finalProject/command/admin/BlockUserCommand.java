package com.vitkovskaya.finalProject.command.admin;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.UserServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockUserCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(RequestContent content) {
        logger.log(Level.DEBUG, "IN BlockUserCommand");
        Router router = new Router();
        UserServiceImpl userService = new UserServiceImpl();
        String start = content.getRequestParameter(ConstantName.PARAMETER_PAGE_START);
        Long id = Long.valueOf(content.getRequestParameter(ConstantName.PARAMETER_ID));
        String page = (String) content.getSessionAttribute(ConstantName.ATTRIBUTE_PAGE_PATH);
        logger.log(Level.DEBUG, page);
        logger.log(Level.DEBUG, start + " START");
        logger.log(Level.DEBUG, id + " CLIENT ID");
        try {
            boolean blocked = userService.blockUser(id);
            logger.log(Level.DEBUG, blocked + "BLOCKED STATUS");
            if (blocked) {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_BLOCK_USER,
                        MessageManager.getProperty(ConstantName.MESSAGE_BLOCK_USER));
                router.setPagePath(page);
                logger.log(Level.DEBUG, router.getPagePath() + " FROM BLOCK COMMAND IF");
                router.setType(RouteType.FORWARD);
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_BLOCK_USER_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_BLOCKING_ERROR));
                router.setPagePath(page);
                logger.log(Level.DEBUG, router.getPagePath() + " FROM BLOCK COMMAND ELSE");
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
