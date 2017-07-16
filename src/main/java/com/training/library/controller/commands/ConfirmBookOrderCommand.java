package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.training.library.controller.utils.Attribute.*;
import static com.training.library.controller.utils.PathManager.LIBRARIAN_HOME_PAGE;

public class ConfirmBookOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ConfirmBookOrderCommand.class);

    private BookOrderService bookOrderService;

    public ConfirmBookOrderCommand(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int bookOrderIdForConfirming = Integer.parseInt(request.getParameter(ORDER_ID));
        Optional<BookOrder> orderFromDB = bookOrderService.find(bookOrderIdForConfirming);
        if (orderFromDB.isPresent()) {
            BookOrder order = orderFromDB.get();
            order.setOrderStatus(BookOrder.OrderStatus.CLOSED);
            order.setDateOfReceive(new Date());
            order.getBook().setBookStatus(Book.BookStatus.UNAVAILABLE);
            bookOrderService.update(order);
        }
        String pageToGo = PathManager.getInstance().getProperty(LIBRARIAN_HOME_PAGE);
        setUnconfirmedBookOrdersList(request);
        return pageToGo;
    }

    private void setUnconfirmedBookOrdersList(HttpServletRequest request) {
        List<BookOrder> bookOrders = bookOrderService.findByStatus(BookOrder.OrderStatus.OPEN);
        request.setAttribute(ORDERS_LIST, bookOrders);
    }
}
