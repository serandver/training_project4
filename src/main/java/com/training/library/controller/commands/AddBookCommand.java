package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBookCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddBookCommand.class);

    private BookService bookService;

    public AddBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);
        String bookTitle = request.getParameter("bookTitle");
        String bookAuthor = request.getParameter("bookAuthor");
        String bookNumber = request.getParameter("bookNumber");
        Book book = new Book.Builder()
                .setTitle(bookTitle)
                .setAuthor(bookAuthor)
                .setInventoryNumber((bookNumber)).build();
        int generatedId = bookService.create(book);

        if (generatedId != -1) {
            pageToGo = new LoadBookCataloguePageCommand().execute(request, response);
        }
        return pageToGo;
    }
}
