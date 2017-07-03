package com.training.library.controller.commands.impl;

import com.training.library.config.PathConfig;
import com.training.library.controller.commands.Command;
import com.training.library.model.BookOrder;
import com.training.library.model.User;
import com.training.library.services.BookOrderService;
import com.training.library.services.impl.BookOrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowMyOrdersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        BookOrderService bookOrderService = new BookOrderServiceImpl();
        User currentUser = (User)session.getAttribute("user");
        int currentUserId = currentUser.getId();

        List<BookOrder> currentUserOrders = bookOrderService.findByUserId(currentUserId);
        request.setAttribute("orderList", currentUserOrders);

        return PathConfig.getInstance().getProperty(PathConfig.MY_ORDERS_PAGE);
    }
}
