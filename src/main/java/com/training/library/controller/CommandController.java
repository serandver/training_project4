package com.training.library.controller;

import com.training.library.controller.commands.Command;
import com.training.library.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CommandController.class);

    private RequestHelper requestHelper = RequestHelper.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType ("text/html; charset=UTF-8");
        request.setCharacterEncoding ("UTF-8");

        String page;
        try {
            Command command = requestHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Servlet CommandController";
    }
}
