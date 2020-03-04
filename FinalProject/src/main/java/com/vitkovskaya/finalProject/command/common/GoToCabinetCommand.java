package com.vitkovskaya.finalProject.command.common;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.entity.UserRole;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToCabinetCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        User user = (User) content.getSessionAttribute(ConstantName.ATTRIBUTE_USER);
        UserRole userRole = user.getUserRole();
        switch (userRole) {
            case ADMIN:
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ADMIN_CABINET));
                break;
            case CLEANER:
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CABINET));
                break;
            case CLIENT:
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_CABINET));
                break;
            default:
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_LOGIN));
        }
        return router;
    }
}
