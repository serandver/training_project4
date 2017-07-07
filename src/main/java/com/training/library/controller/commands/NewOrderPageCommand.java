package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewOrderPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String bookTitle = request.getParameter("bookTitle");
        String bookAuthor = request.getParameter("bookAuthor");
        Book book = new Book.Builder()
                .setId(Integer.parseInt(bookId))
                .setTitle(bookTitle)
                .setAuthor(bookAuthor)
                .build();
        request.setAttribute("book", book);

        return PathManager.getInstance().getProperty(PathManager.ORDER_BOOK_PAGE);
    }
}
