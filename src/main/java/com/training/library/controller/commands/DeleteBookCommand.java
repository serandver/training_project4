package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteBookCommand.class);

    BookService bookService;

    public DeleteBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);
        int bookIdForDeleting = Integer.parseInt(request.getParameter("bookId"));
        bookService.delete(bookIdForDeleting);
        pageToGo = new LoadBookCataloguePageCommand().execute(request, response);
        return pageToGo;
    }
}
