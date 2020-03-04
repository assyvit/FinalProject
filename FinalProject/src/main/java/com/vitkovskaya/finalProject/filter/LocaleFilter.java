package com.vitkovskaya.finalProject.filter;

import com.vitkovskaya.finalProject.command.ConstantName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter (urlPatterns = {"/jsp/*"})
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute(ConstantName.PARAMETER_LOCALE ) == null) {
            session.setAttribute(ConstantName.PARAMETER_LOCALE , ConstantName.PARAMETER_LOCALE_RU);
        }
        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
