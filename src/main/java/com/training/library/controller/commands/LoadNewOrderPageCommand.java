package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.training.library.controller.utils.Attribute.*;

public class LoadNewOrderPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadNewOrderPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String bookId = request.getParameter(BOOK_ID);
        String bookTitle = request.getParameter(BOOK_TITLE);
        String bookAuthor = request.getParameter(BOOK_AUTHOR);

        Book book = getBook(bookId, bookTitle, bookAuthor);
        request.setAttribute(BOOK, book);

        return PathManager.getInstance().getProperty(PathManager.ORDER_BOOK_PAGE);
    }

    private Book getBook(String bookId, String bookTitle, String bookAuthor) {
        return new Book.Builder()
                    .setId(Integer.parseInt(bookId))
                    .setTitle(bookTitle)
                    .setAuthor(bookAuthor)
                    .build();
    }
}
