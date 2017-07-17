package com.training.library.controller.utils;

import com.training.library.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class Validator {

    private static final Logger LOGGER = Logger.getLogger(Validator.class);

    //General Email Regex (RFC 5322 Official Standard)
    private static final String LOGIN_EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    private static final String NAME_REGEX = "^(?=.{1,50}$)[A-Z]{1}[a-z]+$";

    private Validator() {
    }

    private static class Holder{
        static final Validator INSTANCE = new Validator();
    }

    public static Validator getInstance(){
        return Holder.INSTANCE;
    }

    public void validateName(String name, List<ErrorMessage> errors) {
        if (name == null || !name.matches(NAME_REGEX)) {
            ErrorMessage errorMessage = new ErrorMessage.Builder()
                    .addMessage("Wrong name format: ")
                    .addMessage(name)
                    .build();
            errors.add(errorMessage);
            LOGGER.warn(errorMessage.getMessage());
        }
    }

    public void validateEmail(String login, List<ErrorMessage> errors) throws ServiceException {
        if (login == null || !login.matches(LOGIN_EMAIL_REGEX)) {
            ErrorMessage errorMessage = new ErrorMessage.Builder()
                    .addMessage("Wrong email format: ")
                    .addMessage(login)
                    .build();
            errors.add(errorMessage);
            LOGGER.warn(errorMessage.getMessage());
        }
    }

    public void validatePassword(String password, List<ErrorMessage> errors) throws ServiceException {
        if (password == null || !password.matches(PASSWORD_REGEX)) {
            ErrorMessage errorMessage = new ErrorMessage.Builder()
                    .addMessage("Wrong password format")
                    .build();
            errors.add(errorMessage);
            LOGGER.warn(errorMessage.getMessage());
        }
    }

    public void confirm(String password, String confirmPassword, List<ErrorMessage> errors) {
        if (!confirmPassword.equals(password)) {
            ErrorMessage errorMessage = new ErrorMessage.Builder()
                    .addMessage("Passwords should be equal")
                    .build();
            errors.add(errorMessage);
            LOGGER.warn(errorMessage.getMessage());
        }
    }
}
