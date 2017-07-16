package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.BookOrder;
import com.training.library.model.User;
import com.training.library.services.BookOrderService;
import com.training.library.services.impl.BookOrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoadMyOrdersCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadMyOrdersCommand.class);

    private BookOrderService bookOrderService;

    public LoadMyOrdersCommand(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(true);
        User currentUser = (User)session.getAttribute("user");
        int currentUserId = currentUser.getId();

        List<BookOrder> currentUserOrders = bookOrderService.findByUserId(currentUserId);
        request.setAttribute("orderList", currentUserOrders);

        return PathManager.getInstance().getProperty(PathManager.MY_ORDERS_PAGE);
    }
}
