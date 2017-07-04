package com.training.library.controller.commands;

import com.training.library.config.PathConfig;
import com.training.library.controller.commands.Command;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import com.training.library.services.impl.BookOrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.training.library.config.PathConfig.LIBRARIAN_HOME_PAGE;

public class ConfirmBookOrderCommand implements Command {

    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookOrderForIdConfirming = Integer.parseInt(request.getParameter("orderId"));
        Optional<BookOrder> orderFromDB = bookOrderService.find(bookOrderForIdConfirming);
        if (orderFromDB.isPresent()) {
            BookOrder order = orderFromDB.get();
            order.setStatus(BookOrder.Status.CLOSED);
            order.setDateOfReceive(new Date());
            bookOrderService.update(order);
        }
        String pageToGo = PathConfig.getInstance().getProperty(LIBRARIAN_HOME_PAGE);
        setUnconfirmedBookOrdersList(request);
        return pageToGo;
    }

    private void setUnconfirmedBookOrdersList(HttpServletRequest request) {
        List<BookOrder> bookOrders = bookOrderService.findByStatus(BookOrder.Status.OPEN);
        request.setAttribute("orderList", bookOrders);
    }
}
