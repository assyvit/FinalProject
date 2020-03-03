package com.vitkovskaya.finalProject.command.client;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.util.ConfigurationManager;

public class GoToClientCabinetCommand implements Command {
    @Override
    public Router execute(RequestContent request) {
        Router router = new Router();
       router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_CABINET));
       router.setType(RouteType.FORWARD);
        return router;
    }
}
