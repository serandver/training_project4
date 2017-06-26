package com.training.library.model.services;

import com.training.library.model.entities.BookOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookOrderService {
    Optional<BookOrder> find(int id);
    List<BookOrder> findAll();
    int create(BookOrder bookOrder);
    void update(BookOrder bookOrder);
    int delete(int id);
    List<BookOrder> findByUserId(int userId);
    List<BookOrder> findByBookId(int bookId);
    List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place);
    List<BookOrder> findByDateOfReceive(Date date);
    List<BookOrder> findByDateOfReturn(Date date);
}
