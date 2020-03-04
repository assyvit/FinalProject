package com.vitkovskaya.finalProject.filter;

import com.vitkovskaya.finalProject.command.CommandType;
import com.vitkovskaya.finalProject.command.ConstantName;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.entity.UserRole;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

/**
 * The {@code AuthorizationFilter} class
 * is an implementation of {@code Filter} interface.
 * Checks user role and command if it is accessible for user role.
 */
@WebFilter(urlPatterns = {"/controller/*"},
        dispatcherTypes = {
                DispatcherType.FORWARD,
                DispatcherType.REQUEST
        }
)
public class AuthorizationFilter implements Filter {
    private final static Logger logger = LogManager.getLogger();
    private final EnumSet<CommandType> commonCommands =
            EnumSet.range(CommandType.CHANGE_LANGUAGE, CommandType.SHOW_CATALOG);
    private final EnumSet<CommandType> userCommands =
            EnumSet.range(CommandType.UPLOAD_IMAGE, CommandType.GO_TO_CHANGE_PASSWORD);
    private final EnumSet<CommandType> adminCommands =
            EnumSet.range(CommandType.SHOW_CLIENTS, CommandType.SHOW_BLOCKED_CLIENTS);
    private final EnumSet<CommandType> clientCommands =
            EnumSet.range(CommandType.CONFIRM_ORDER, CommandType.GO_TO_ORDER);
    private final EnumSet<CommandType> cleanerCommands =
            EnumSet.range(CommandType.ADD_CLEANING, CommandType.GO_TO_ADD_CLEANING);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String command = request.getParameter(ConstantName.PARAM_NAME_COMMAND);
        logger.log(Level.DEBUG, command + " COMMAND");
        if (command != null) {
            if (session.getAttribute(ConstantName.ATTRIBUTE_USER) != null) {
                User user = (User) session.getAttribute(ConstantName.ATTRIBUTE_USER);
                UserRole userRole = user.getUserRole();
                logger.log(Level.DEBUG, userRole);
                switch (userRole) {
                    case CLIENT:
                        if (clientCommands.contains(CommandType.valueOf(command.toUpperCase())) ||
                                commonCommands.contains(CommandType.valueOf(command.toUpperCase()))
                                || userCommands.contains(CommandType.valueOf(command.toUpperCase()))) {
                            filterChain.doFilter(request, response);
                        } else {
                            logger.log(Level.INFO, "Command is not available for Client");
                            response.sendRedirect(request.getContextPath() + ConfigurationManager.getProperty(ConstantName.JSP_MAIN));
                        }
                        break;
                    case CLEANER:
                        if (cleanerCommands.contains(CommandType.valueOf(command.toUpperCase())) ||
                                commonCommands.contains(CommandType.valueOf(command.toUpperCase()))
                                || userCommands.contains(CommandType.valueOf(command.toUpperCase()))) {
                            filterChain.doFilter(request, response);
                        } else {
                            logger.log(Level.INFO, "Command is not available for Cleaner");
                            response.sendRedirect(request.getContextPath() + ConstantName.COMMAND_MAIN);
                    //        return;
                        }
                        break;
                    case ADMIN:
                        if (adminCommands.contains(CommandType.valueOf(command.toUpperCase())) ||
                                commonCommands.contains(CommandType.valueOf(command.toUpperCase()))
                                || userCommands.contains(CommandType.valueOf(command.toUpperCase()))) {
                            filterChain.doFilter(request, response);
                        } else {
                            logger.log(Level.INFO, "Command is not available for Admin");
                            response.sendRedirect(request.getContextPath() + ConstantName.COMMAND_MAIN);
                         //   return;
                        }
                        break;
                    default:
                        logger.log(Level.DEBUG, command + " DEFAULT");
                        response.sendRedirect(request.getContextPath() + ConstantName.COMMAND_MAIN);
                }
            } else {
                logger.log(Level.DEBUG, command + "if NO USER ");
                if (commonCommands.contains(CommandType.valueOf(command.toUpperCase()))) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    logger.log(Level.INFO, "Command is not available for not authorized user");

                    RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantName.COMMAND_MAIN);
                    dispatcher.forward(request, response);
                    return;
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}