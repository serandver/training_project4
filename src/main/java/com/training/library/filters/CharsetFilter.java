package com.training.library.filters;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private FilterConfig filterConfig;
    private String requestEncoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        requestEncoding = filterConfig.getInitParameter("requestEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(requestEncoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
