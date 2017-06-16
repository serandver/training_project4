package com.training.library.model.dao;

import com.training.library.model.entities.Book;
import com.training.library.model.entities.BookOrder;
import com.training.library.model.entities.User;

import java.util.List;

public interface BookOrderDao extends GenericDao<BookOrder> {
    List<BookOrder> findByUserId(int userId);
    List<BookOrder> findByBookId(int bookId);
    List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place);
    List<BookOrder> findByStatus(boolean isReturned);
}
