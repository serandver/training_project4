package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditBookCommand implements Command {

    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);;

        String bookId = request.getParameter("bookId");
        String bookTitle = request.getParameter("bookTitle");
        String bookAuthor = request.getParameter("bookAuthor");
        String bookNumber = request.getParameter("bookNumber");
        Book bookForUpdating = new Book.Builder()
                .setId(Integer.parseInt(bookId))
                .setTitle(bookTitle)
                .setAuthor(bookAuthor)
                .setInventoryNumber(bookNumber).build();
        int result = bookService.update(bookForUpdating);
        if (result == 1) {
            pageToGo = "/books";
        }
        return pageToGo;
    }
}
