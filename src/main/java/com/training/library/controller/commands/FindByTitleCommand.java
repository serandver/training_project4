package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindByTitleCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(FindByTitleCommand.class);
    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String titleForSearching = request.getParameter("title");

        List<Book> orders = bookService.findByTitle(titleForSearching);
        request.setAttribute("bookList", orders);

        return PathManager.getInstance().getProperty(PathManager.READER_HOME_PAGE);
    }
}
