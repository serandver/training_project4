package com.training.library.controller.commands;

import com.training.library.controller.utils.Attribute;
import com.training.library.controller.utils.ErrorMessage;
import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookOrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.training.library.controller.utils.Attribute.*;
import static com.training.library.controller.utils.LogMessage.BOOK_ALREADY_ORDERED;
import static com.training.library.controller.utils.PathManager.LIBRARIAN_HOME_PAGE;

public class ConfirmBookOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ConfirmBookOrderCommand.class);

    private BookOrderService bookOrderService;
    private BookService bookService;

    public ConfirmBookOrderCommand(BookOrderService bookOrderService, BookService bookService) {
        this.bookOrderService = bookOrderService;
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);

        int bookOrderIdForConfirming = Integer.parseInt(request.getParameter(ORDER_ID));
        Optional<BookOrder> orderFromDB = bookOrderService.find(bookOrderIdForConfirming);
        if (orderFromDB.isPresent()) {
            BookOrder order = orderFromDB.get();
            Book book = order.getBook();
            if (isBookAvailable(book)) {
                giveBookToReader(order);
            }
            else {
                LOGGER.error(BOOK_ALREADY_ORDERED);
                ErrorMessage errorMessage = getErrorMessage(order, book);
                request.setAttribute(Attribute.MESSAGE_ERROR, errorMessage);
            }
            pageToGo = new LibrarianHomePageCommand(BookOrderServiceImpl.getInstance()).execute(request, response);
        }
        setUnconfirmedBookOrdersList(request);
        return pageToGo;
    }

    private boolean isBookAvailable(Book book) {
        boolean isAvailable = false;

        Optional<Book> bookOptional = bookService.find(book.getId());
        if (bookOptional.isPresent()) {
            Book bookFromDB = bookOptional.get();
            Book.BookStatus currentStatus = bookFromDB.getBookStatus();
            if (currentStatus == Book.BookStatus.AVAILABLE) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    private void giveBookToReader(BookOrder order) {
        order.setOrderStatus(BookOrder.OrderStatus.CLOSED);
        order.setDateOfReceive(new Date());
        order.getBook().setBookStatus(Book.BookStatus.UNAVAILABLE);
        bookOrderService.update(order);
    }

    private void setUnconfirmedBookOrdersList(HttpServletRequest request) {
        List<BookOrder> bookOrders = bookOrderService.findByStatus(BookOrder.OrderStatus.OPEN);
        request.setAttribute(ORDERS_LIST, bookOrders);
    }

    private ErrorMessage getErrorMessage(BookOrder order, Book book) {
        ErrorMessage errorMessage = new ErrorMessage.Builder()
                .addMessage("Book ")
                .addMessage(String.valueOf(book.getId()))
                .addMessage(" is already ordered. You should delete order ")
                .addMessage(String.valueOf(order.getId()))
                .build();
        return errorMessage;
    }
}
