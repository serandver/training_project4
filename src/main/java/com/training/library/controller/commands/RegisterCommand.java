package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.User;
import com.training.library.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.training.library.controller.utils.Attribute.*;
import static com.training.library.controller.utils.PathManager.ERROR_PAGE;
import static com.training.library.controller.utils.PathManager.LOGIN_PAGE;

public class RegisterCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = PathManager.getInstance().getProperty(ERROR_PAGE);
        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);

        if (confirm(password, confirmPassword)) {
            User user = new User.Builder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
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
