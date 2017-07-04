package com.training.library.controller.commands;

import com.training.library.config.PathConfig;
import com.training.library.controller.commands.Command;
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
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.training.library.config.PathConfig.*;

public class LoginCommand implements Command{
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD ="password";

    private UserService userService = UserServiceImpl.getInstance();
    private BookService bookService = BookServiceImpl.getInstance();
    private BookOrderService bookOrderService = BookOrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pageToGo = PathConfig.getInstance().getProperty(ERROR_PAGE);
        String email = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);

        if( email != null && password != null ){
            Optional<User> result;
            result = userService.login(email, password);
            if(result.isPresent()){
                User user = result.get();
                User.Role role = user.getRole();
                request.getSession().setAttribute("user", user);
                if(role == User.Role.READER) {
                    pageToGo = PathConfig.getInstance().getProperty(READER_HOME_PAGE);
                    setBookCatalogForReader(request);
                }
                else if(role == User.Role.LIBRARIAN){
                    pageToGo = PathConfig.getInstance().getProperty(LIBRARIAN_HOME_PAGE);
                    setListBookOrders(request);
                }
            }
        }
        return pageToGo;
    }

    private void setBookCatalogForReader(HttpServletRequest request) {
        List<Book> booksAvailableForOrder = bookService.findAllAvailableForOrderBooks();
        request.setAttribute("bookList", booksAvailableForOrder);
    }

    private void setListBookOrders(HttpServletRequest request) {
        List<BookOrder> bookOrders = bookOrderService.findByStatus(BookOrder.Status.OPEN);
        request.setAttribute("orderList", bookOrders);
    }
}
