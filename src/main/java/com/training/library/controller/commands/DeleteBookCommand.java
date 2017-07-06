package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookService bookService = BookServiceImpl.getInstance();
        int bookIdForDeleting = Integer.parseInt(request.getParameter("bookId"));
        bookService.delete(bookIdForDeleting);
        return "/books";
    }
}
