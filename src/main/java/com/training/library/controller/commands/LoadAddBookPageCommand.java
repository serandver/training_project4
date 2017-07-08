package com.training.library.controller.commands;

import com.training.library.config.PathManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoadAddBookPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PathManager.getInstance().getProperty(PathManager.ADD_BOOK_PAGE);
    }
}
