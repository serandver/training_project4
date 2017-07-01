package com.training.library.controller.commands.impl;

import com.training.library.config.PathConfig;
import com.training.library.controller.commands.Command;
import com.training.library.model.User;
import com.training.library.services.UserService;
import com.training.library.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.training.library.config.PathConfig.CATALOGUE_PAGE;
import static com.training.library.config.PathConfig.ERROR_PAGE;

public class LoginCommand implements Command{
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD ="password";

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pageToGo = PathConfig.getInstance().getProperty(ERROR_PAGE);
        String email = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);

        if( email != null && password != null ){
            Optional<User> user;
            user = userService.login(email, password);
            if(user.isPresent()){
                request.getSession().setAttribute("user", user.get());
                pageToGo = PathConfig.getInstance().getProperty(CATALOGUE_PAGE);;
            }
        }
        return pageToGo;
    }
}
