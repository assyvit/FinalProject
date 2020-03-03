package com.vitkovskaya.finalProject.command.client;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaner;
import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.CleanerServiceImpl;
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
        CleanerServiceImpl cleanerService = new CleanerServiceImpl();
        ClientServiceImpl clientService = new ClientServiceImpl();
        User user = (User) content.getSessionAttribute(ConstantName.PARAMETER_USER);
        Long clientId = user.getUserId();
        logger.log(Level.DEBUG, clientId);
        try {
            Optional<Client> clientOptional = clientService.findById(clientId);

            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                logger.log(Level.DEBUG, client);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_VALIDATED_MAP, client);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_PROFILE));
                router.setType(RouteType.FORWARD);
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_EDIT_PROFILE_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_PROFILE_SHOW_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CABINET));
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
