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
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.training.library.controller.utils.Attribute.*;

public class OrderBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(OrderBookCommand.class);
    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();
    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute(USER);
        int currentUserId = currentUser.getId();
        Optional<User> userResult = userService.find(currentUserId);
        User user = null;
        if(userResult.isPresent()) {
            user = userResult.get();
        }

        Book book = null;
        int bookId = Integer.parseInt(request.getParameter(BOOK_ID));
        Optional<Book> bookResult = bookService.find(bookId);
        if(bookResult.isPresent()) {
            book = bookResult.get();
        }

        String placeString = request.getParameter(ORDER_PLACE);
        BookOrder.ReadingPlace place = BookOrder.ReadingPlace.valueOf(placeString);

        BookOrder newOrder = null;
        if (user != null && book != null) {
            newOrder = new BookOrder.Builder()
                    .setUser(user)
                    .setBook(book)
                    .setPlace(place)
                    .setOrderStatus(BookOrder.OrderStatus.OPEN)
                    .build();
            int generatedOrderId = bookOrderService.create(newOrder);
            if (generatedOrderId != -1) {
                pageToGo = new ReaderHomePageCommand(BookServiceImpl.getInstance()).execute(request, response);
            }
        }
        return pageToGo;
    }
}
