package com.vitkovskaya.finalProject.command;

import com.vitkovskaya.finalProject.util.ConfigurationManager;

public class EmptyCommand implements Command {


    @Override
    public Router execute(RequestContent request) {
        Router router = new Router();
        String page = ConfigurationManager.getProperty("path.page.index");
        return router;
    }
}

