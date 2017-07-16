package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.training.library.controller.utils.Attribute.ORDERS_LIST;

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
        request.setAttribute(ORDERS_LIST, orders);
        return PathManager.getInstance().getProperty(PathManager.ORDERS_PAGE);
    }
}
