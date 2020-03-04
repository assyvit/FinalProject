package com.vitkovskaya.finalProject.filter;

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
/**
 * The {@codeSecureFilter} class
 * is an implementation of {@code Filter} interface.
 * Checks user role and .
 */
//@WebFilter(urlPatterns = {"/jsp/admin/*"
////                          "/jsp/client/*",
////                          "/jsp/cleaner/*"
//}
////        dispatcherTypes = {DispatcherType.FORWARD,
////        DispatcherType.REQUEST}
//        )

public class SecureFilter implements Filter {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("SecureFilter");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.log(Level.DEBUG, "IN ADMIN PAGE FILTER");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute(ConstantName.ATTRIBUTE_USER) != null);
        if (loggedIn) {
            User user = (User) session.getAttribute(ConstantName.ATTRIBUTE_USER);
            UserRole userRole = user.getUserRole();
            logger.debug(userRole);
//            if( userRole == UserRole.ADMIN){
//
//            }
            switch (userRole) {
                case CLIENT:
                    logger.debug(request.getContextPath());
                    RequestDispatcher dispatcher = request.getServletContext()
                            .getRequestDispatcher(ConstantName.COMMAND_MAIN);
                    dispatcher.forward(request, response);
                    break;
                case CLEANER:
                    dispatcher = request.getServletContext()
                            .getRequestDispatcher(ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CABINET));
                    dispatcher.forward(request, response);
                    break;
                case ADMIN:
                 filterChain.doFilter(request, response);
//                    dispatcher = request.getServletContext()
//                            .getRequestDispatcher(ConfigurationManager.getProperty(ConstantName.JSP_ADMIN_CABINET));
//                    dispatcher.forward(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() +
                            ConfigurationManager.getProperty(ConstantName.JSP_LOGIN));
            }
        }
        else {
            response.sendRedirect(request.getContextPath() +
                    ConfigurationManager.getProperty(ConstantName.JSP_MAIN));
        }
    }

    @Override
    public void destroy() {

    }
}

