package com.vitkovskaya.finalProject.command.common;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.util.ConfigurationManager;

public class MainCommand implements Command {
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_MAIN));
        return router;
    }
}
