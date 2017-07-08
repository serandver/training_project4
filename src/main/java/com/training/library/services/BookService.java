package com.training.library.services;

import com.training.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> find(int id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findAll();
    List<Book> findAllAvailableForOrderBooks();
    int create(Book book);
    int update(Book book);
    int delete(int id);
}
