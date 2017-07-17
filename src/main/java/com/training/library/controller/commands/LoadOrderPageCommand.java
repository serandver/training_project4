package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import com.training.library.model.BookOrder;
import com.training.library.model.User;
import com.training.library.services.BookOrderService;
import com.training.library.services.BookService;
import com.training.library.services.UserService;
import com.training.library.services.impl.BookOrderServiceImpl;
import com.training.library.services.impl.BookServiceImpl;
import com.training.library.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import static com.training.library.controller.utils.Attribute.*;

public class LoadOrderPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadOrderPageCommand.class);

    private BookService bookService;
    private UserService userService;

    public LoadOrderPageCommand(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);;

        String orderId = request.getParameter(ORDER_ID);
        String userId = request.getParameter(USER_ID);
        String bookId = request.getParameter(BOOK_ID);
        String dateReceive = request.getParameter(ORDER_DATE_RECEIVE);
        String dateReturn = request.getParameter(ORDER_DATE_RETURN);
        String place = request.getParameter(ORDER_PLACE);
        String orderStatus = request.getParameter(ORDER_STATUS);

        Book book = getBookById(bookId);
        User user = getUserById(userId);

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        Date dateOfReceiving = getDateFromString(dateReceive, formatter);
        Date dateOfReturning = getDateFromString(dateReturn, formatter);

        if (user != null && book != null) {
            BookOrder orderForUpdating = new BookOrder.Builder()
                    .setId(Integer.parseInt(orderId))
                    .setUser(user)
                    .setBook(book)
                    .setDateOfReceive(dateOfReceiving)
                    .setDateOfReturn(dateOfReturning)
                    .setPlace(BookOrder.ReadingPlace.valueOf(place))
                    .setOrderStatus(BookOrder.OrderStatus.valueOf(orderStatus)).build();
            request.setAttribute(ORDER, orderForUpdating);
            pageToGo = PathManager.getInstance().getProperty(PathManager.EDIT_ORDER_PAGE);
        }
        return pageToGo;
    }

    private Book getBookById(String bookId) {
        Book book = null;
        Optional<Book> bookResult = bookService.find(Integer.parseInt(bookId));
        if(bookResult.isPresent()) {
            book = bookResult.get();
        }
        return book;
    }

    private User getUserById(String userId) {
        User user = null;
        Optional<User> userResult = userService.find(Integer.parseInt(userId));
        if(userResult.isPresent()) {
            user = userResult.get();
        }
        return user;
    }

    private Date getDateFromString(String dateReceive, DateFormat formatter) {
        Date dateOfReceiving = null;
        try {
            dateOfReceiving = formatter.parse(dateReceive);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateOfReceiving;
    }
}
