package com.training.library.controller.utils;

import org.apache.log4j.Logger;

public class Validator {
    private static final Logger LOGGER = Logger.getLogger(Validator.class);

    private static final String LOGIN_EMAIL_REGEX =
            "^(?=.{1,50}$)[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,20}$";
    private static final String FIRST_NAME_REGEX = "^(?=.{1,50}$)[A-Z]{1}[a-z]+$";
    private static final String LAST_NAME_REGEX = "^(?=.{1,50}$)[A-Z]{1}[a-z]+([-][A-Z]{1}[a-z]+)?$";

    private Validator() {
    }

    private static class Holder{
        static final Validator INSTANCE = new Validator();
    }

    public static Validator getInstance(){
        return Holder.INSTANCE;
    }
}
