package com.training.library.services.impl;

import com.training.library.dao.BookDao;
import com.training.library.dao.DaoFactory;
import com.training.library.model.Book;
import com.training.library.services.BookService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);
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
    public List<Book> findByTitle(String title) {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.findByAuthor(author);
    }

    @Override
    public List<Book> findAll() {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.findAll();
    }

    @Override
    public List<Book> findByStatus(Book.BookStatus status) {
        BookDao bookDao = daoFactory.createBookDao();
        return bookDao.findByStatus(status);
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
