package com.training.library.dao;

import com.training.library.entities.Book;

import java.util.Optional;

public interface BookDao extends GenericDao<Book> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);
}
