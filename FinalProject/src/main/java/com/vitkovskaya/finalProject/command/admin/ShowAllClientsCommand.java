package com.vitkovskaya.finalProject.command.admin;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.ClientServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The {@code ShowAllClientsCommand} class
 * is a command to show clients page.
 */
public class ShowAllClientsCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Gets all clients from the database,
     * sets the session attribute to show them and
     * returns router to the clients page.
     *
     * @param content an {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see ClientServiceImpl#findAllClients()
     */
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        ClientServiceImpl clientService = new ClientServiceImpl();
        List<Client> clientList;
        try {
            clientList = clientService.findAllClients();
            logger.log(Level.DEBUG, clientList);
            if (!clientList.isEmpty()) {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_CLIENT_LIST, clientList);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_SHOW_CLIENT));
                router.setType(RouteType.FORWARD);
            } else {
                content.addRequestAttribute(ConstantName.ATTRIBUTE_SHOW_CLIENT_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_SHOW_CLIENT_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ADMIN_CABINET));
                router.setType(RouteType.FORWARD);// FIXME: 29.01.2020
            }
        } catch (ServiceException e) {
            logger.error("Error while executing command", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
