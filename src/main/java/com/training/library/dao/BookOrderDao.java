package com.training.library.dao;

import com.training.library.entities.BookOrder;

import java.util.Date;
import java.util.List;

public interface BookOrderDao extends GenericDao<BookOrder> {
    List<BookOrder> findByUserId(int userId);
    List<BookOrder> findByBookId(int bookId);
    List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place);
    List<BookOrder> findByDateOfReceive(Date date);
    List<BookOrder> findByDateOfReturn(Date date);
}
