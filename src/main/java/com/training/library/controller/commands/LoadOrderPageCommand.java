package com.training.library.controller.commands;

import com.training.library.config.PathManager;
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

public class LoadOrderPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoadOrderPageCommand.class);

    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();
    private BookService bookService = BookServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);;

        String orderId = request.getParameter("orderId");
        String userId = request.getParameter("userId");
        String bookId = request.getParameter("bookId");
        String dateReceive = request.getParameter("dateReceive");
        String dateReturn = request.getParameter("dateReturn");
        String place = request.getParameter("readPlace");
        String orderStatus = request.getParameter("orderStatus");

        Book book = null;
        Optional<Book> bookResult = bookService.find(Integer.parseInt(bookId));
        if(bookResult.isPresent()) {
            book = bookResult.get();
        }

        User user = null;
        Optional<User> userResult = userService.find(Integer.parseInt(userId));
        if(userResult.isPresent()) {
            user = userResult.get();
        }

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        Date dateOfReceiving = null;
        Date dateOfReturning = null;

        try {
            dateOfReceiving = (Date)formatter.parse(dateReceive);
            dateOfReturning = (Date)formatter.parse(dateReturn);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (user != null && book != null) {
            BookOrder orderForUpdating = new BookOrder.Builder()
                    .setId(Integer.parseInt(orderId))
                    .setUser(user)
                    .setBook(book)
                    .setDateOfReceive(dateOfReceiving)
                    .setDateOfReturn(dateOfReturning)
                    .setPlace(BookOrder.ReadingPlace.valueOf(place))
                    .setOrderStatus(BookOrder.OrderStatus.valueOf(orderStatus)).build();
            request.setAttribute("order", orderForUpdating);
            pageToGo = PathManager.getInstance().getProperty(PathManager.EDIT_ORDER_PAGE);
        }
        return pageToGo;
    }
}
