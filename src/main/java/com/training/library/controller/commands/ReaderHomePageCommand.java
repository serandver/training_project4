package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.training.library.controller.utils.Attribute.BOOKS_LIST;
import static com.training.library.controller.utils.PathManager.READER_HOME_PAGE;

public class ReaderHomePageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ReaderHomePageCommand.class);

    private BookService bookService;

    public ReaderHomePageCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Book> booksAvailableForOrder = bookService.findByStatus(Book.BookStatus.AVAILABLE);
        request.setAttribute(BOOKS_LIST, booksAvailableForOrder);
        return PathManager.getInstance().getProperty(READER_HOME_PAGE);
    }
}
