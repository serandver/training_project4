package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.User;
import com.training.library.services.UserService;
import com.training.library.services.impl.BookOrderServiceImpl;
import com.training.library.services.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.training.library.controller.utils.Attribute.*;
import static com.training.library.controller.utils.PathManager.*;

public class LoginCommand implements Command{

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String pageToGo = PathManager.getInstance().getProperty(ERROR_PAGE);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        if( email != null && password != null ){
            Optional<User> result;
            result = userService.login(email, password);
            if(result.isPresent()){
                User user = result.get();
                User.Role role = user.getRole();
                request.getSession().setAttribute(USER, user);
                if(role == User.Role.READER) {
                    pageToGo = new ReaderHomePageCommand(BookServiceImpl.getInstance()).execute(request, response);
                }
                else if(role == User.Role.LIBRARIAN){
                    pageToGo = new LibrarianHomePageCommand(BookOrderServiceImpl.getInstance()).execute(request, response);
                }
            }
        }
        return pageToGo;
    }
}
