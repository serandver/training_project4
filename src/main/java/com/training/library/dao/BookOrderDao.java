package com.training.library.dao;

import com.training.library.model.entities.Book;
import com.training.library.model.entities.BookOrder;
import com.training.library.model.entities.User;

import java.util.Date;
import java.util.List;

public interface BookOrderDao extends GenericDao<BookOrder> {

    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place);

    public List<BookOrder> findByUser(User user);

    public List<BookOrder> findByBook(Book book);

    public List<BookOrder> findByStatus(BookOrder.OrderStatus status);

    public List<BookOrder> findAllBetweenDates(Date from, Date to);
}
