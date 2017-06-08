package com.training.library.model.dao;

import com.training.library.model.entities.Book;
import com.training.library.model.entities.BookOrder;
import com.training.library.model.entities.User;

import java.util.List;

public interface BookOrderDao extends GenericDao<BookOrder> {
    
    public List<BookOrder> findByUser(User user);

    public List<BookOrder> findByBook(Book book);

    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place);

    public List<BookOrder> findByStatus(boolean isReturned);
}
