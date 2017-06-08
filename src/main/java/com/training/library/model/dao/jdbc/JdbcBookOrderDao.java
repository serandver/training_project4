package com.training.library.model.dao.jdbc;

import com.training.library.model.dao.BookOrderDao;
import com.training.library.model.entities.Book;
import com.training.library.model.entities.BookOrder;
import com.training.library.model.entities.User;

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
    public int create(BookOrder bookOrder) {
        return 0;
    }

    @Override
    public int update(BookOrder bookOrder) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
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
    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place) {
        return null;
    }

    @Override
    public List<BookOrder> findByStatus(boolean isReturned) {
        return null;
    }
}
