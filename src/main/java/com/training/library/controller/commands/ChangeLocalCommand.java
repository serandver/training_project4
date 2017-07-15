package com.training.library.controller.commands;

import com.training.library.config.PathManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocalCommand implements Command {
    private static final String EN = "en";
    private static final String RU = "ru";
    private static final String EN_LOCALE = "en_US";
    private static final String RU_LOCALE = "ru_RU";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter("locale");
        setLocale(locale, request);
        return PathManager.getInstance().getProperty(PathManager.START_PAGE);
    }

    private void setLocale(String locale, HttpServletRequest request){
        if(locale != null) {
            if (locale.equals(EN)) {
                request.getSession().setAttribute("locale", EN_LOCALE);
            } else if (locale.equals(RU)) {
                request.getSession().setAttribute("locale", RU_LOCALE);
            }
        }
    }
}
