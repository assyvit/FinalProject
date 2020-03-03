package com.vitkovskaya.finalProject.filter;

import com.vitkovskaya.finalProject.command.ConstantName;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.entity.UserRole;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(urlPatterns = "/jsp/admin/*")
// FIXME: 14.02.2020 //Защищаемае часть
public class AdminFilter implements Filter {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("AdminFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        //Существует ли сессиия
        boolean loggedIn = session != null && session.getAttribute(ConstantName.ATTRIBUTE_USER) != null;

        if (loggedIn) {
            //Если существует то получаем роль
            User user = (User) session.getAttribute(ConstantName.ATTRIBUTE_USER);
            UserRole userRole = user.getUserRole();
            logger.debug(userRole);
            switch (userRole) {
                case CLIENT:
                    logger.debug(request.getContextPath());
                    response.sendRedirect(request.getContextPath() +
                            ConfigurationManager.getProperty(ConstantName.JSP_CLIENT_CABINET));
                    break;
                case CLEANER:
                    response.sendRedirect(request.getContextPath() +
                            ConfigurationManager.getProperty(ConstantName.JSP_CLEANER_CABINET));
                    break;
                case ADMIN:
                    response.sendRedirect(request.getContextPath() +
                            ConfigurationManager.getProperty(ConstantName.JSP_ADMIN_CABINET));
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "jsp/index.jsp");
            }
        }


//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        RoleType role = (RoleType) request.getSession().getAttribute(Constants.SESSION_ROLE);
//        if (role != RoleType.ADMIN) {
//            RequestDispatcher dispatcher = request.getServletContext()
//                    .getRequestDispatcher(RoutePath.INDEX_PAGE_PATH.getRoutePath());
//            dispatcher.forward(request, response);
//        } else {
//            filterChain.doFilter(request, response);
//        }

    }

    @Override
    public void destroy() {

    }
}

