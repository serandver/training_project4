package com.training.library.controller.utils;

import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ErrorMessage {

    private static final Logger LOGGER = Logger.getLogger(ErrorMessage.class);

    private static final Locale EN_LOCALE = new Locale("en", "US");
    private static final Locale RU_LOCALE = new Locale("ru", "RU");
    private static final String BUNDLE_NAME = "messages";
    private static ResourceBundle messagesBundle = ResourceBundle.getBundle(BUNDLE_NAME, EN_LOCALE);;

    private String message;

    public static class Builder {
        private ErrorMessage errorMessage = new ErrorMessage();
        private StringBuilder sbMessage = new StringBuilder();

        public Builder addMessage(String messageKey){
            sbMessage.append(getMessageForDto(messagesBundle, messageKey));
            return this;
        }
        private String getMessageForDto(ResourceBundle bundle, String key){
            String message;
            try {
                message = bundle.getString(key);
            } catch (MissingResourceException e) {
                message = key;
            }
            return message;
        }
        public ErrorMessage build(){
            errorMessage.setMessage(sbMessage.toString());
            return errorMessage;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessage(String key){
        return messagesBundle.getString(key);
    }

    public static void setLocale(Locale locale) {
        try {
            messagesBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        } catch (Exception e) {
            LOGGER.error(LogMessage.RB_READ_ERROR + BUNDLE_NAME + locale);
        }
    }
}
