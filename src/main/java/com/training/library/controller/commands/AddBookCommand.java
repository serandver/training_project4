package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.training.library.controller.utils.Attribute.*;

public class AddBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AddBookCommand.class);

    private BookService bookService;

    public AddBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);
        String bookTitle = request.getParameter(BOOK_TITLE);
        String bookAuthor = request.getParameter(BOOK_AUTHOR);
        String bookNumber = request.getParameter(BOOK_NUMBER);
        Book book = new Book.Builder()
                .setTitle(bookTitle)
                .setAuthor(bookAuthor)
                .setInventoryNumber((bookNumber)).build();
        int generatedId = bookService.create(book);

        if (generatedId != -1) {
            pageToGo = new LoadBookCataloguePageCommand(BookServiceImpl.getInstance()).execute(request, response);
        }
        return pageToGo;
    }
}
