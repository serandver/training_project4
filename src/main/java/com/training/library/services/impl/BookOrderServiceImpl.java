package com.training.library.services.impl;

import com.training.library.dao.DaoFactory;
import com.training.library.entities.BookOrder;
import com.training.library.services.BookOrderService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookOrderServiceImpl implements BookOrderService {

    private DaoFactory daoFactory;

    public BookOrderServiceImpl() {
        this.daoFactory = DaoFactory.getInstance();
    }

    public BookOrderServiceImpl(DaoFactory instance) {
        this.daoFactory = instance;
    }

    @Override
    public Optional<BookOrder> find(int id) {
       return daoFactory.createBookOrderDao().find(id);
    }

    @Override
    public List<BookOrder> findAll() {
        return daoFactory.createBookOrderDao().findAll();
    }

    @Override
    public int create(BookOrder bookOrder) {
        return daoFactory.createBookOrderDao().create(bookOrder);
    }

    @Override
    public void update(BookOrder bookOrder) {
        daoFactory.createBookOrderDao().update(bookOrder);
    }

    @Override
    public int delete(int id) {
        return daoFactory.createBookOrderDao().delete(id);
    }

    @Override
    public List<BookOrder> findByUserId(int userId) {
        return daoFactory.createBookOrderDao().findByUserId(userId);
    }

    @Override
    public List<BookOrder> findByBookId(int bookId) {
        return daoFactory.createBookOrderDao().findByBookId(bookId);
    }

    @Override
    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place) {
        return daoFactory.createBookOrderDao().findByReadingPlace(place);
    }

    @Override
    public List<BookOrder> findByDateOfReceive(Date date) {
        return null;
    }

    @Override
    public List<BookOrder> findByDateOfReturn(Date date) {
        return null;
    }
}
