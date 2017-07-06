package com.training.library.services.impl;

import com.training.library.dao.BookDao;
import com.training.library.dao.DaoFactory;
import com.training.library.model.Book;
import com.training.library.services.BookService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private DaoFactory daoFactory;

    public BookServiceImpl(DaoFactory instance) {
        this.daoFactory = instance;
    }

    private static class Holder{
        static final BookServiceImpl INSTANCE = new BookServiceImpl( DaoFactory.getInstance() );
    }

    public static BookServiceImpl getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public Optional<Book> find(int id) {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.find(id);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.findByTitle(title);
    }

    @Override
    public Optional<Book> findByAuthor(String author) {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.findByAuthor(author);
    }

    @Override
    public List<Book> findAll() {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.findAll();
    }

    @Override
    public List<Book> findAllAvailableForOrderBooks() {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.findAllAvailableForOrderBooks();
    }

    @Override
    public int create(Book book) {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.create(book);
    }

    @Override
    public int update(Book book) {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.update(book);
    }

    @Override
    public int delete(int id) {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.delete(id);
    }
}
