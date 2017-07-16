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

public class FindByTitleCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(FindByTitleCommand.class);

    private BookService bookService;

    public FindByTitleCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String titleForSearching = request.getParameter(SEARCH_TITLE);

        List<Book> orders = bookService.findByTitle(titleForSearching);
        request.setAttribute(BOOKS_LIST, orders);

        return PathManager.getInstance().getProperty(PathManager.READER_HOME_PAGE);
    }
}
