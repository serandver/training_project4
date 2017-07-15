package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.training.library.config.PathManager.READER_HOME_PAGE;

public class ReaderHomePageCommand implements Command {

    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> booksAvailableForOrder = bookService.findAllAvailableForOrderBooks();
        request.setAttribute("bookList", booksAvailableForOrder);
        return PathManager.getInstance().getProperty(READER_HOME_PAGE);
    }
}
