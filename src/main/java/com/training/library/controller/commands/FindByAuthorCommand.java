package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindByAuthorCommand implements Command {

    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorForSearching = request.getParameter("author");

        List<Book> orders = bookService.findByAuthor(authorForSearching);
        request.setAttribute("bookList", orders);

        return PathManager.getInstance().getProperty(PathManager.READER_HOME_PAGE);
    }
}
