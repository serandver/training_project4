package com.training.library.dao.jdbc;

import com.training.library.dao.BookDao;
import com.training.library.model.entities.Book;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcBookDao implements BookDao {
    private static final String SELECT_FROM_BOOKS = "SELECT * FROM books";
    private static final String SELECT_FROM_BOOKS_BY_ID = "SELECT * FROM books WHERE id = ?";
    private static final String SELECT_FROM_BOOKS_BY_TITLE = "SELECT * FROM books WHERE title = ?";
    private static final String SELECT_FROM_BOOKS_BY_AUTHOR = "SELECT * FROM books WHERE author = ?";
    private static final String BOOK_ID = "book_id";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";

    private Connection connection;

    public JdbcBookDao() {
    }

    public JdbcBookDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Book> find(int id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void create(Book book) {

    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }
}
