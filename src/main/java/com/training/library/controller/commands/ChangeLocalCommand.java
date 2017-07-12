package com.training.library.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocalCommand implements Command {
    private static final String EN = "en";
    private static final String RU = "ru";
    private static final String EN_LOCALE = "en_US";
    private static final String RU_LOCALE = "ru_RU";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = request.getParameter("url");
        String query = request.getParameter("query");
        String fullURL = URL + "?" + query;
        String locale = request.getParameter("locale");
        setLocale(locale, request);
        return fullURL;
//        String requestURL = request.getRequestURL().toString();
//        String requestURI = request.getRequestURI();
//        String pathInfo = request.getPathInfo();
//        String contextPath = request.getContextPath();
//        String localName = request.getLocalName();
//        String r = request.getServletContext().getContextPath();
//        Object string = request.getSession().getAttribute("locale");
//        String queryString = request.getQueryString();
//        if (queryString != null) {
//            requestURL = new StringBuilder()
//                    .append(requestURL)
//                    .append("?")
//                    .append(queryString).toString();
//        }
//        String locale = request.getParameter("locale");
//        setLocale(locale, request);
//        return requestURL;
    }

    private void setLocale(String locale, HttpServletRequest request){
        if(locale != null) {
            if (locale.equals(EN)) {
                request.getSession().setAttribute("locale", EN_LOCALE);
            } else if (locale.equals(RU)) {
                request.getSession().setAttribute("locale", RU_LOCALE);
            }
        }
    }
}
