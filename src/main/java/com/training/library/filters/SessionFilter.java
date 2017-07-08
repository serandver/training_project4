package com.training.library.filters;

import com.training.library.config.PathManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String page;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session != null && !session.isNew()) {
            chain.doFilter(request, response);
        } else {
            page = PathManager.getInstance().getProperty(PathManager.LOGIN_PAGE);
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
