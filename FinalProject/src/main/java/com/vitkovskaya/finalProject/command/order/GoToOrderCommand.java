package com.vitkovskaya.finalProject.command.order;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.util.ConfigurationManager;

public class GoToOrderCommand implements Command {
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ORDER));
        router.setType(RouteType.FORWARD);
        return router;
    }
}
