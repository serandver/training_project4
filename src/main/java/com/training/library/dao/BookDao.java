package com.training.library.dao;

import com.training.library.model.Book;

import java.util.Optional;

public interface BookDao extends GenericDao<Book> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);
}
