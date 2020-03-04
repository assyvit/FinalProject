package com.vitkovskaya.finalProject.command.admin;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.UserServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserStatusCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets user status which administrator wants to change from the request.
     * Edits user value (updates database) and returns router to the same page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see UserServiceImpl#changeUserStatus(long, boolean)
     */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        UserServiceImpl userService = new UserServiceImpl();
        String start = content.getRequestParameter(ConstantName.PARAMETER_PAGE_START);
        Long id = Long.valueOf(content.getRequestParameter(ConstantName.PARAMETER_ID));
        String page = (String) content.getSessionAttribute(ConstantName.ATTRIBUTE_PAGE_PATH);
        Boolean status = Boolean.valueOf(content.getRequestParameter(ConstantName.PARAMETER_ACTIVE_STATUS));
        try {
            if (userService.changeUserStatus(id, status)) {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                router.setPagePath(page);
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_BLOCK_USER_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_BLOCKING_ERROR));
                router.setPagePath(page);
            }
        } catch (ServiceException e) {
            logger.error("Error while changing user status", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
