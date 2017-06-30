package com.training.library.controller;

import com.training.library.controller.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    RequestHelper requestHelper = RequestHelper.getRequestHelperInstance();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType ("text/html; charset=UTF-8");
        request.setCharacterEncoding ("UTF-8");

        String page = null;

        try {
            Command command = requestHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//        PrintWriter out = response.getWriter();
//        out.print("Hello!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Servlet Controller";
    }
}
