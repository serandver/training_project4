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

public class LoadBookCataloguePageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadBookCataloguePageCommand.class);

    private BookService bookService;

    public LoadBookCataloguePageCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        List<Book> orders = bookService.findAll();
        request.setAttribute(BOOKS_LIST, orders);

        return PathManager.getInstance().getProperty(PathManager.CATALOGUE_PAGE);
    }
}
