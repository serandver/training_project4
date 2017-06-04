package com.training.library.dao.jdbc;

import com.training.library.dao.BookOrderDao;
import com.training.library.model.entities.Book;
import com.training.library.model.entities.BookOrder;
import com.training.library.model.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JdbcBookOrderDao implements BookOrderDao{
    @Override
    public Optional<BookOrder> find(int id) {
        return null;
    }

    @Override
    public List<BookOrder> findAll() {
        return null;
    }

    @Override
    public void create(BookOrder bookOrder) {

    }

    @Override
    public void update(BookOrder bookOrder) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place) {
        return null;
    }

    @Override
    public List<BookOrder> findByUser(User user) {
        return null;
    }

    @Override
    public List<BookOrder> findByBook(Book book) {
        return null;
    }

    @Override
    public List<BookOrder> findByStatus(BookOrder.OrderStatus status) {
        return null;
    }

    @Override
    public List<BookOrder> findAllBetweenDates(Date from, Date to) {
        return null;
    }
}
