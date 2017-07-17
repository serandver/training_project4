package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.training.library.controller.utils.Attribute.BOOK_ID;

public class DeleteBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteBookCommand.class);

    private BookService bookService;

    public DeleteBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);
        int bookIdForDeleting = Integer.parseInt(request.getParameter(BOOK_ID));
        bookService.delete(bookIdForDeleting);
        pageToGo = new LoadBookCataloguePageCommand(BookServiceImpl.getInstance()).execute(request, response);
        return pageToGo;
    }
}
