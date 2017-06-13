package com.training.library.model.dao;

import com.training.library.model.entities.Book;

import java.util.Optional;

public interface BookDao extends GenericDao<Book> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);
}
