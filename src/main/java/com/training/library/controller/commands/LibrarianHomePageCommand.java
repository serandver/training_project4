package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import com.training.library.services.impl.BookOrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.training.library.config.PathManager.LIBRARIAN_HOME_PAGE;

public class LibrarianHomePageCommand implements Command{

    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookOrder> bookOrders = bookOrderService.findByStatus(BookOrder.Status.OPEN);
        request.setAttribute("orderList", bookOrders);
        return PathManager.getInstance().getProperty(LIBRARIAN_HOME_PAGE);
    }
}
