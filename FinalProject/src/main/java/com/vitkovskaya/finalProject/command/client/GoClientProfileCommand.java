package com.vitkovskaya.finalProject.command.client;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.ClientServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GoClientProfileCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        ClientServiceImpl clientService = new ClientServiceImpl();
        User user = (User) content.getSessionAttribute(ConstantName.PARAMETER_USER);
        Long clientId = user.getUserId();
        try {
            Optional<Client> clientOptional = clientService.findById(clientId);
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                content.addRequestAttribute(ConstantName.ATTRIBUTE_USER_PROFILE, client);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_PROFILE));
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_EDIT_PROFILE_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_PROFILE_SHOW_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_CABINET));
            }
        } catch (ServiceException e) {
            logger.error("Error while executing command", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
