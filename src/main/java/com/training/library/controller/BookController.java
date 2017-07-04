package com.training.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookController extends HttpServlet {

    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonBookFromRequest = request.getParameter("bookAuthor");
        Book book = mapper.readValue(jsonBookFromRequest, Book.class);
        System.out.println(book);
    }
}
