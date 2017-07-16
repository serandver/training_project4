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

public class EditOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditOrderCommand.class);

    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();
    private BookService bookService = BookServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

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

        Date dateOfReceiving = getDateFromString(dateReceive, new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US));
        Date dateOfReturning = getDateFromString(dateReturn, new SimpleDateFormat("yyyy-MM-dd"));

        if (user != null && book != null) {
            BookOrder orderForUpdating = new BookOrder.Builder()
                    .setId(Integer.parseInt(orderId))
                    .setUser(user)
                    .setBook(book)
                    .setDateOfReceive(dateOfReceiving)
                    .setDateOfReturn(dateOfReturning)
                    .setPlace(BookOrder.ReadingPlace.valueOf(place))
                    .setOrderStatus(BookOrder.OrderStatus.valueOf(orderStatus)).build();

            int result = bookOrderService.update(orderForUpdating);
            if (result == 1) {
                pageToGo = new LoadOrdersManagementPageCommand(BookOrderServiceImpl.getInstance()).execute(request, response);
            }
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

    private Date getDateFromString(String date, DateFormat formatter) {
        Date dateOfReceiving = null;
        try {
            dateOfReceiving = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateOfReceiving;
    }
}
