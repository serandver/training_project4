package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import com.training.library.services.impl.BookOrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LoadOrdersManagementPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadOrdersManagementPageCommand.class);

    private BookOrderService bookOrderService;

    public LoadOrdersManagementPageCommand(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<BookOrder> orders = bookOrderService.findAll();
        Collections.sort(orders, new Comparator<BookOrder>() {
            @Override
            public int compare(BookOrder o1, BookOrder o2) {
                return o1.getId() - o2.getId();
            }
        });
        request.setAttribute("orders", orders);
        return PathManager.getInstance().getProperty(PathManager.ORDERS_PAGE);
    }
}
