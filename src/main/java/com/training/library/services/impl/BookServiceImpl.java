package com.training.library.services.impl;

import com.training.library.dao.DaoFactory;
import com.training.library.entities.Book;
import com.training.library.services.BookService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private DaoFactory daoFactory;

    public BookServiceImpl() {
        this.daoFactory = DaoFactory.getInstance();
    }

    public BookServiceImpl(DaoFactory instance) {
        this.daoFactory = instance;
    }

    @Override
    public Optional<Book> find(int id) {
        return daoFactory.createBookDao().find(id);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return daoFactory.createBookDao().findByTitle(title);
    }

    @Override
    public Optional<Book> findByAuthor(String author) {
        return daoFactory.createBookDao().findByAuthor(author);
    }

    @Override
    public List<Book> findAll() {
        return daoFactory.createBookDao().findAll();
    }

    @Override
    public int create(Book book) {
        return daoFactory.createBookDao().create(book);
    }

    @Override
    public void update(Book book) {
        daoFactory.createBookDao().update(book);
    }

    @Override
    public int delete(int id) {
        return daoFactory.createBookDao().delete(id);
    }
}
