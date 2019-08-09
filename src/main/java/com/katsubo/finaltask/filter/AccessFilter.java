package com.katsubo.finaltask.filter;

import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AccessFilter.class);
    private static final String COMMAND = "command";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter(COMMAND);
        Access access = Access.getInstance();
        try {
            if (command != null && access.can(command, request)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                logger.log(Level.WARN, "no access for this user");
                dispatch(servletRequest, servletResponse, ResourceManager.getProperty("page.error405"));
                return;
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARN, "illegal command: " + command);
            dispatch(servletRequest, servletResponse, ResourceManager.getProperty("page.error404"));
            return;
        }

    }

    private void dispatch(ServletRequest servletRequest, ServletResponse servletResponse, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/" + page);
        dispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
