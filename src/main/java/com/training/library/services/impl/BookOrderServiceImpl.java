package com.training.library.services.impl;

import com.training.library.dao.BookDao;
import com.training.library.dao.BookOrderDao;
import com.training.library.dao.DaoFactory;
import com.training.library.model.Book;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookOrderServiceImpl implements BookOrderService {

    private static final Logger LOGGER = Logger.getLogger(BookOrderServiceImpl.class);
    private DaoFactory daoFactory;

    public BookOrderServiceImpl(DaoFactory instance) {
        this.daoFactory = instance;
    }

    private static class Holder{
        static final BookOrderServiceImpl INSTANCE = new BookOrderServiceImpl( DaoFactory.getInstance() );
    }

    public static BookOrderServiceImpl getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public Optional<BookOrder> find(int id) {
        LOGGER.info("Find order by id ");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.find(id);
    }

    @Override
    public List<BookOrder> findAll() {
        LOGGER.info("Find all orders ");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findAll();
    }

    @Override
    public int create(BookOrder bookOrder) {
        LOGGER.info("Create order");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.create(bookOrder);
    }

    @Override
    public int update(BookOrder bookOrder) {
        LOGGER.info("Update order");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        BookDao bookDao = daoFactory.createBookDao();
        Book bookFromOrder = bookOrder.getBook();
        bookDao.update(bookFromOrder);
        return bookOrderDao.update(bookOrder);
    }

    @Override
    public int delete(int id) {
        LOGGER.info("Delete order");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.delete(id);
    }

    @Override
    public List<BookOrder> findByUserId(int userId) {
        LOGGER.info("Find order by user id: ");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByUserId(userId);
    }

    @Override
    public List<BookOrder> findByBookId(int bookId) {
        LOGGER.info("Find order by book id: ");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByBookId(bookId);
    }

    @Override
    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place) {
        LOGGER.info("Find order by reading place ");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByReadingPlace(place);
    }

    @Override
    public List<BookOrder> findByDateOfReceive(Date date) {
        LOGGER.info("Find order by date of receive");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByDateOfReceive(date);
    }

    @Override
    public List<BookOrder> findByDateOfReturn(Date date) {
        LOGGER.info("Find order by date of return");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByDateOfReturn(date);
    }

    @Override
    public List<BookOrder> findByStatus(BookOrder.OrderStatus orderStatus) {
        LOGGER.info("Find order by status");
        BookOrderDao bookOrderDao = daoFactory.createBookOrderDao();
        return bookOrderDao.findByOrderStatus(orderStatus);
    }
}
