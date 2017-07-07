package com.training.library.controller;

import com.training.library.config.PathManager;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import com.training.library.services.impl.BookOrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.training.library.config.PathManager.LIBRARIAN_HOME_PAGE;

public class LibrarianHomeController extends HttpServlet {

    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookOrder> bookOrders = bookOrderService.findByStatus(BookOrder.Status.OPEN);
        request.setAttribute("orderList", bookOrders);
        String pageToGo = PathManager.getInstance().getProperty(LIBRARIAN_HOME_PAGE);
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageToGo);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
