package com.training.library.controller;

import com.training.library.controller.commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CommandController.class);
    private static final String EN = "en";
    private static final String RU = "ru";
    private static final String EN_LOCALE = "en_US";
    private static final String RU_LOCALE = "ru_RU";

    private RequestHelper requestHelper = RequestHelper.getRequestHelperInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        localeHandling(request);
        String path = request.getServletPath();
        Command command = requestHelper.getCommand(path);
        String page;
        try {
            page = command.execute(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void localeHandling(HttpServletRequest request){
        request.setAttribute("previousPath", request.getServletPath());
        String locale = request.getParameter("locale");
        setLocale(locale, request);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Servlet CommandController";
    }
}
