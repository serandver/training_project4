package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.training.library.controller.utils.Attribute.LOCALE;

public class ChangeLocalCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChangeLocalCommand.class);

    private static final String EN = "en";
    private static final String RU = "ru";
    private static final String EN_LOCALE = "en_US";
    private static final String RU_LOCALE = "ru_RU";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String locale = request.getParameter(LOCALE);
        setLocale(locale, request);
        return PathManager.getInstance().getProperty(PathManager.START_PAGE);
    }

    private void setLocale(String locale, HttpServletRequest request){
        if(locale != null) {
            if (locale.equals(EN)) {
                request.getSession().setAttribute(LOCALE, EN_LOCALE);
            } else if (locale.equals(RU)) {
                request.getSession().setAttribute(LOCALE, RU_LOCALE);
            }
        }
    }
}
