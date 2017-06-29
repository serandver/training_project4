package com.training.library.controller.commands.impl;

import com.training.library.controller.commands.Command;
import com.training.library.model.entities.User;
import com.training.library.services.UserService;
import com.training.library.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command{
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD ="password";

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageToGo = "/index.jsp";

        String email = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);

        if( email != null && password != null ){
            Optional<User> user;
            user = userService.login(email, password);
            if(user.isPresent()){
                request.getSession().setAttribute("user", user.get());
                pageToGo = "/rest/library";
            }
        }
        return pageToGo;
    }
}
