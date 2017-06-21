package com.training.library.model.services;

import com.training.library.model.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> find(int id);
    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);
    List<Book> findAll();
    int create(Book book);
    int update(Book book);
    int delete(int id);
}
