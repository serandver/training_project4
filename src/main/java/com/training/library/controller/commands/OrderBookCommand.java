package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.model.Book;
import com.training.library.model.BookOrder;
import com.training.library.model.User;
import com.training.library.services.BookOrderService;
import com.training.library.services.BookService;
import com.training.library.services.UserService;
import com.training.library.services.impl.BookOrderServiceImpl;
import com.training.library.services.impl.BookServiceImpl;
import com.training.library.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class OrderBookCommand implements Command {

    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();
    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("user");
        int currentUserId = currentUser.getId();
        Optional<User> userResult = userService.find(currentUserId);
        User user = null;
        if(userResult.isPresent()) {
            user = userResult.get();
        }

        Book book = null;
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Optional<Book> bookResult = bookService.find(bookId);
        if(bookResult.isPresent()) {
            book = bookResult.get();
        }

        String placeString = request.getParameter("orderPlace");
        BookOrder.ReadingPlace place = BookOrder.ReadingPlace.valueOf(placeString);


        if (user != null && book != null) {
            BookOrder newOrder = new BookOrder.Builder()
                    .setUser(user)
                    .setBook(book)
                    .setPlace(place)
                    .setStatus(BookOrder.Status.OPEN)
                    .build();
            int generatedOrderId = bookOrderService.create(newOrder);
            if (generatedOrderId != -1) {
                pageToGo = "/reader";
            }
        }
        return pageToGo;
    }
}
