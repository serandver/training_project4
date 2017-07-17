package com.training.library.controller.commands;

import com.training.library.controller.utils.Attribute;
import com.training.library.controller.utils.ErrorMessage;
import com.training.library.controller.utils.PathManager;
import com.training.library.controller.utils.Validator;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.User;
import com.training.library.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static com.training.library.controller.utils.Attribute.*;
import static com.training.library.controller.utils.PathManager.ERROR_PAGE;
import static com.training.library.controller.utils.PathManager.LOGIN_PAGE;

public class RegisterCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private UserService userService;
    private Validator validator;
    private List<ErrorMessage> errors;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
        this.validator = Validator.getInstance();
        this.errors = new ArrayList<>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = PathManager.getInstance().getProperty(ERROR_PAGE);
        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);

        errors.clear();
        validator.validateName(firstName, errors);
        validator.validateName(lastName, errors);
        validator.validateEmail(email, errors);
        validator.validatePassword(password, errors);
        validator.validatePassword(confirmPassword, errors);
        validator.confirm(password, confirmPassword, errors);

        if (errors.isEmpty()) {
            User user = buildNewUser(firstName, lastName, email, password);
            int createdUserId = userService.create(user);
            if (createdUserId != -1) {
                page = PathManager.getInstance().getProperty(LOGIN_PAGE);
            }
        }
        else {
            request.setAttribute(Attribute.LIST_ERROR, errors);
            page = new LoadSignUpPageCommand().execute(request, response);
        }
        return page;
    }

    private User buildNewUser(String firstName, String lastName, String email, String password) {
        return new User.Builder()
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setEmail(email)
                        .setPassword(password)
                        .setRole(User.Role.READER)
                        .build();
    }
}
