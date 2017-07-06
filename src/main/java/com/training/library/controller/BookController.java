package com.training.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.training.library.config.PathManager;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class BookController extends HttpServlet {

    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);;
        String bookId = request.getParameter("bookId");
        String bookTitle = request.getParameter("bookTitle");
        String bookAuthor = request.getParameter("bookAuthor");
        String bookNumber = request.getParameter("bookNumber");
        Book bookForUpdating = new Book.Builder()
                .setId(Integer.parseInt(bookId))
                .setTitle(bookTitle)
                .setAuthor(bookAuthor)
                .setInventoryNumber(bookNumber).build();
        int result = bookService.update(bookForUpdating);
        if (result == 1) {
            pageToGo = PathManager.getInstance().getProperty(PathManager.CATALOGUE_PAGE);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageToGo);
        response.sendRedirect("/books");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> orders = bookService.findAll();
        request.setAttribute("bookList", orders);
        String page = PathManager.getInstance().getProperty(PathManager.CATALOGUE_PAGE);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
