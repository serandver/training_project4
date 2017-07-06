package com.training.library.controller.commands;


import com.training.library.config.PathManager;
import com.training.library.model.User;
import com.training.library.services.UserService;
import com.training.library.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.training.library.config.PathManager.ERROR_PAGE;
import static com.training.library.config.PathManager.LOGIN_PAGE;

public class RegisterCommand implements Command {
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PathManager.getInstance().getProperty(ERROR_PAGE);
        String firstname = new String(request.getParameter(FIRSTNAME).getBytes("UTF-8"), "UTF-8");
        String lastname = new String(request.getParameter(LASTNAME).getBytes("UTF-8"), "UTF-8");
        String email = new String(request.getParameter(EMAIL).getBytes("UTF-8"), "UTF-8");
        String password = new String(request.getParameter(PASSWORD).getBytes("UTF-8"), "UTF-8");
        String confirmPassword = new String(request.getParameter(CONFIRM_PASSWORD).getBytes("UTF-8"), "UTF-8");

        if (confirm(password, confirmPassword)) {
            UserService userService = UserServiceImpl.getInstance();
            User user = new User.Builder()
                    .setFirstName(firstname)
                    .setLastName(lastname)
                    .setEmail(email)
                    .setPassword(password)
                    .setRole(User.Role.READER)
                    .build();
            int createdUserId = userService.create(user);
            if (createdUserId != -1) {
                page = PathManager.getInstance().getProperty(LOGIN_PAGE);
            }
        }
        return page;
    }

    private boolean confirm(String password, String confirmPassword) {
        return confirmPassword.equals(password);
    }
}
