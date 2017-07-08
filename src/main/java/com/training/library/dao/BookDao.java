package com.training.library.dao;

import com.training.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends GenericDao<Book> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findAllAvailableForOrderBooks();
}
