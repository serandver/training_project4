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

public class EditBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditBookCommand.class);

    private BookService bookService;

    public EditBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);;
        String bookId = request.getParameter(BOOK_ID);
        String bookTitle = request.getParameter(BOOK_TITLE);
        String bookAuthor = request.getParameter(BOOK_AUTHOR);
        String bookNumber = request.getParameter(BOOK_NUMBER);
        Book.BookStatus status = bookService.find(Integer.parseInt(bookId)).get().getBookStatus();
        Book bookForUpdating = new Book.Builder()
                .setId(Integer.parseInt(bookId))
                .setTitle(bookTitle)
                .setAuthor(bookAuthor)
                .setInventoryNumber(bookNumber)
                .setBookStatus(status).build();
        int result = bookService.update(bookForUpdating);
        if (result == 1) {
            pageToGo = new LoadBookCataloguePageCommand(BookServiceImpl.getInstance()).execute(request, response);
        }
        return pageToGo;
    }
}
