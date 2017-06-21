package com.training.library.model.services.impl;

import com.training.library.model.dao.DaoFactory;
import com.training.library.model.entities.BookOrder;

import java.util.List;
import java.util.Optional;

public class BookOrderServiceImpl {

    private DaoFactory daoFactory;

    private static class Holder{
        static final BookOrderServiceImpl INSTANCE = new BookOrderServiceImpl( DaoFactory.getInstance() );
    }

    BookOrderServiceImpl(DaoFactory instance) {
        this.daoFactory = instance;
    }

    public static BookOrderServiceImpl getInstance(){
        return Holder.INSTANCE;
    }

    public Optional<BookOrder> find(int id) {
       return daoFactory.createBookOrderDao().find(id);
    }

    public List<BookOrder> findAll() {
        return daoFactory.createBookOrderDao().findAll();
    }

    public int create(BookOrder bookOrder) {
        return daoFactory.createBookOrderDao().create(bookOrder);
    }

    public int update(BookOrder bookOrder) {
        return daoFactory.createBookOrderDao().update(bookOrder);
    }

    public int delete(int id) {
        return daoFactory.createBookOrderDao().delete(id);
    }

    public List<BookOrder> findByUserId(int userId) {
        return daoFactory.createBookOrderDao().findByUserId(userId);
    }

    public List<BookOrder> findByBookId(int bookId) {
        return daoFactory.createBookOrderDao().findByBookId(bookId);
    }

    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place) {
        return daoFactory.createBookOrderDao().findByReadingPlace(place);
    }

    public List<BookOrder> findByStatus(boolean isReturned) {
        return daoFactory.createBookOrderDao().findByStatus(isReturned);
    }
}
