package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.training.library.controller.utils.Attribute.*;

public class FindByAuthorCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(FindByAuthorCommand.class);

    private BookService bookService;

    public FindByAuthorCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String authorForSearching = request.getParameter(SEARCH_AUTHOR);

        List<Book> orders = bookService.findByAuthor(authorForSearching);
        request.setAttribute(BOOKS_LIST, orders);

        return PathManager.getInstance().getProperty(PathManager.READER_HOME_PAGE);
    }
}
