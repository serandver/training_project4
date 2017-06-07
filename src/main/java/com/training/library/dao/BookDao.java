package com.training.library.dao;

import com.training.library.model.entities.Book;

import java.util.List;

public interface BookDao extends GenericDao<Book> {

    public List<Book> findByTitle(String title);

    public List<Book> findByAuthor(String author);
}
