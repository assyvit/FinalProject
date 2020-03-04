package com.vitkovskaya.finalProject.controller;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.command.factory.CommandFactory;
import com.vitkovskaya.finalProject.pool.ConnectionPool;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * The {@code Controller} class is a main HttpServlet.
 * Overrides doPost and doGet methods by calling
 * the own method processRequest(request, response).
 */
@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Do get.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      Signals that an I/O exception has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Do post.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      Signals that an I/O exception has occurred.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * This method is called by doGet and doPost methods.
     * Gets the command from the request, and calls method execute(request) on it that has
     * own implementation for each command.
     * Sets the type how request and response should be processed after this controller or
     * redirect a response to the error page if CommandException or ServiceException occurs.
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made
     *                 of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends
     *                 to the client
     * @throws IOException      if an input or output error is detected when the servlet handles
     *                          the request
     * @throws ServletException if the request could not be handled
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        RequestContent content = new RequestContent(request);
        Optional<Command> commandOptional = commandFactory.defineCommand(content);
        if (commandOptional.isPresent()) {
            Command command = commandOptional.get();
            logger.log(Level.DEBUG, command);
            Router router = command.execute(content);
            content.insertValues(request);
            if (router.getPagePath() != null) {
                logger.log(Level.DEBUG, router.getPagePath() + " PAGE PATH IN CONTROLLER");
                if (router.getType().equals(RouteType.FORWARD)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + router.getPagePath());
                }
            } else {
                response.sendRedirect(request.getContextPath() +
                        ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            }
        }
    }

    /**
     * Inits the servlet and connectionPool.
     *
     * @throws ServletException the servlet exception
     */
    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionPool.getInstance();
        logger.debug("Initializing controller");
    }

    /**
     * Destroy the servlet and close connectionPool.
     */
    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().closePool();
        logger.debug("Destroying controller");
    }
}
