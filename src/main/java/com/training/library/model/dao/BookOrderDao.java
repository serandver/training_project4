package com.training.library.model.dao;

import com.training.library.model.entities.Book;
import com.training.library.model.entities.BookOrder;
import com.training.library.model.entities.User;

import java.util.List;

public interface BookOrderDao extends GenericDao<BookOrder> {
    List<BookOrder> findByUser(User user);
    List<BookOrder> findByBook(Book book);
    List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place);
    List<BookOrder> findByStatus(boolean isReturned);
}
