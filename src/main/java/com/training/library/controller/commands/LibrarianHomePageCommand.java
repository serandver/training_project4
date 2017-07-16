package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.training.library.controller.utils.Attribute.ORDERS_LIST;
import static com.training.library.controller.utils.PathManager.LIBRARIAN_HOME_PAGE;

public class LibrarianHomePageCommand implements Command{

    private static final Logger LOGGER = Logger.getLogger(LibrarianHomePageCommand.class);

    private BookOrderService bookOrderService;

    public LibrarianHomePageCommand(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<BookOrder> bookOrders = bookOrderService.findByStatus(BookOrder.OrderStatus.OPEN);
        request.setAttribute(ORDERS_LIST, bookOrders);
        return PathManager.getInstance().getProperty(LIBRARIAN_HOME_PAGE);
    }
}
