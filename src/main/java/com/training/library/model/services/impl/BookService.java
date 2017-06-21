package com.training.library.model.services.impl;

import com.training.library.model.dao.DaoFactory;
import com.training.library.model.entities.Book;

import java.util.List;
import java.util.Optional;

public class BookService {
    private DaoFactory daoFactory;

    private static class Holder{
        static final BookService INSTANCE = new BookService( DaoFactory.getInstance() );
    }

    BookService(DaoFactory instance) {
        this.daoFactory = instance;
    }

    public static BookService getInstance(){
        return Holder.INSTANCE;
    }

    public Optional<Book> find(int id) {
        return daoFactory.createBookDao().find(id);
    }

    public Optional<Book> findByTitle(String title) {
        return daoFactory.createBookDao().findByTitle(title);
    }

    public Optional<Book> findByAuthor(String author) {
        return daoFactory.createBookDao().findByAuthor(author);
    }

    public List<Book> findAll() {
        return daoFactory.createBookDao().findAll();
    }


    public int create(Book book) {
        return daoFactory.createBookDao().create(book);
    }

    public int update(Book book) {
        return daoFactory.createBookDao().update(book);
    }

    public int delete(int id) {
        return daoFactory.createBookDao().delete(id);
    }

}
