package com.training.library.controller;

import com.training.library.config.PathManager;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.training.library.config.PathManager.READER_HOME_PAGE;

public class ReaderHomeController extends HttpServlet {
    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToGo = PathManager.getInstance().getProperty(READER_HOME_PAGE);
        List<Book> booksAvailableForOrder = bookService.findAllAvailableForOrderBooks();
        request.setAttribute("bookList", booksAvailableForOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageToGo);
        dispatcher.forward(request, response);
    }
}
