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
import java.util.Optional;

public class LoadConfirmPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadConfirmPageCommand.class);
    private UserService userService = UserServiceImpl.getInstance();
    private BookService bookService = BookServiceImpl.getInstance();
    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);

        String orderId = request.getParameter("orderId");
        String bookId = request.getParameter("bookId");
        String userId = request.getParameter("userId");
        String readingPlaceString = request.getParameter("orderPlace");

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

        String placeString = request.getParameter("orderPlace");
        BookOrder.ReadingPlace place = BookOrder.ReadingPlace.valueOf(placeString);

        BookOrder bookOrder = null;
        if (user != null && book != null) {
            bookOrder = new BookOrder.Builder()
                    .setId(Integer.parseInt(orderId))
                    .setUser(user)
                    .setBook(book)
                    .setPlace(place)
                    .setOrderStatus(BookOrder.OrderStatus.CLOSED)
                    .build();
            if (bookResult != null) {
                request.setAttribute("order", bookOrder);
                pageToGo = PathManager.getInstance().getProperty(PathManager.CONFIRM_ORDER_PAGE);
            }
        }
        return pageToGo;
    }
}
