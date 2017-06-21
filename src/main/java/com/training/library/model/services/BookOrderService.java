package com.training.library.model.services;

import com.training.library.model.entities.BookOrder;

import java.util.List;
import java.util.Optional;

public interface BookOrderService {
    Optional<BookOrder> find(int id);
    List<BookOrder> findAll();
    int create(BookOrder bookOrder);
    int update(BookOrder bookOrder);
    int delete(int id);
    List<BookOrder> findByUserId(int userId);
    List<BookOrder> findByBookId(int bookId);
    List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place);
    List<BookOrder> findByStatus(boolean isReturned);
}
