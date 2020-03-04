package com.vitkovskaya.finalProject.command.cleaner;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.util.ConfigurationManager;

public class GoToAddCleaning implements Command {
    @Override
    public Router execute(RequestContent content) {
        Router router =new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ADD_CLEANING));
        return router;
    }
}
