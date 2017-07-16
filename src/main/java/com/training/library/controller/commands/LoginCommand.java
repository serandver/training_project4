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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.training.library.config.PathManager.*;

public class LoginCommand implements Command{
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private UserService userService = UserServiceImpl.getInstance();

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD ="password";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String pageToGo = PathManager.getInstance().getProperty(ERROR_PAGE);
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
                    pageToGo = new ReaderHomePageCommand().execute(request, response);
                }
                else if(role == User.Role.LIBRARIAN){
                    pageToGo = new LibrarianHomePageCommand().execute(request, response);
                }
            }
        }
        return pageToGo;
    }
}
