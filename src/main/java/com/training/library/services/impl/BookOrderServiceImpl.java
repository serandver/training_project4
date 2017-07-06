package com.training.library.services.impl;

import com.training.library.dao.BookOrderDao;
import com.training.library.dao.DaoFactory;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookOrderServiceImpl implements BookOrderService {

    private DaoFactory daoFactory;

    public BookOrderServiceImpl(DaoFactory instance) {
        this.daoFactory = instance;
    }

    private static class Holder{
        static final BookOrderServiceImpl INSTANCE = new BookOrderServiceImpl( DaoFactory.getInstance() );
    }

    public static BookOrderServiceImpl getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public Optional<BookOrder> find(int id) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.find(id);
    }

    @Override
    public List<BookOrder> findAll() {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findAll();
    }

    @Override
    public int create(BookOrder bookOrder) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.create(bookOrder);
    }

    @Override
    public int update(BookOrder bookOrder) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.update(bookOrder);
    }

    @Override
    public int delete(int id) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.delete(id);
    }

    @Override
    public List<BookOrder> findByUserId(int userId) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByUserId(userId);
    }

    @Override
    public List<BookOrder> findByBookId(int bookId) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByBookId(bookId);
    }

    @Override
    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByReadingPlace(place);
    }

    @Override
    public List<BookOrder> findByDateOfReceive(Date date) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByDateOfReceive(date);
    }

    @Override
    public List<BookOrder> findByDateOfReturn(Date date) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByDateOfReturn(date);
    }

    @Override
    public List<BookOrder> findByStatus(BookOrder.Status status) {
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByStatus(status);
    }
}
