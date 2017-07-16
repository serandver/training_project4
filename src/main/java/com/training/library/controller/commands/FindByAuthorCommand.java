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
import java.util.List;

public class FindByAuthorCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(FindByAuthorCommand.class);

    private BookService bookService;

    public FindByAuthorCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String authorForSearching = request.getParameter("author");

        List<Book> orders = bookService.findByAuthor(authorForSearching);
        request.setAttribute("bookList", orders);

        return PathManager.getInstance().getProperty(PathManager.READER_HOME_PAGE);
    }
}
