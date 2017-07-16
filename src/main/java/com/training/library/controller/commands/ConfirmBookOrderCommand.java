package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import com.training.library.services.impl.BookOrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.training.library.config.PathManager.LIBRARIAN_HOME_PAGE;

public class ConfirmBookOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ConfirmBookOrderCommand.class);
    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int bookOrderForIdConfirming = Integer.parseInt(request.getParameter("orderId"));
        Optional<BookOrder> orderFromDB = bookOrderService.find(bookOrderForIdConfirming);
        if (orderFromDB.isPresent()) {
            BookOrder order = orderFromDB.get();
            Book book = order.getBook();
            if (isBookInstanceAvailable(book)) {
                order.setOrderStatus(BookOrder.OrderStatus.CLOSED);
                order.setDateOfReceive(new Date());
                bookOrderService.update(order);
            }
            else {
                throw new ServiceException("This book is unavailable");
            }


        }
        String pageToGo = PathManager.getInstance().getProperty(LIBRARIAN_HOME_PAGE);
        setUnconfirmedBookOrdersList(request);
        return pageToGo;
    }

    private boolean isBookInstanceAvailable(Book book) {
        return false;
    }

    private void setUnconfirmedBookOrdersList(HttpServletRequest request) {
        List<BookOrder> bookOrders = bookOrderService.findByStatus(BookOrder.OrderStatus.OPEN);
        request.setAttribute("orderList", bookOrders);
    }
}
