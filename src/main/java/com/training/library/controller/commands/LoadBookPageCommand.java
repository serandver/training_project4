package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.training.library.controller.utils.Attribute.*;

public class LoadBookPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadBookPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String bookId = request.getParameter(BOOK_ID);
        String bookTitle = request.getParameter(BOOK_TITLE);
        String bookAuthor = request.getParameter(BOOK_AUTHOR);
        String bookNumber = request.getParameter(BOOK_NUMBER);
        Book bookForUpdating = new Book.Builder()
                .setId(Integer.parseInt(bookId))
                .setTitle(bookTitle)
                .setAuthor(bookAuthor)
                .setInventoryNumber(bookNumber).build();
        request.setAttribute(BOOK, bookForUpdating);

        return PathManager.getInstance().getProperty(PathManager.EDIT_BOOK_PAGE);
    }
}
