package com.training.library.model.dao.jdbc;

import com.training.library.model.connection.QueryJDBC;
import com.training.library.model.entities.Book;
import com.training.library.model.dao.BookDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookDao implements BookDao {

    private static final String INSERT = "INSERT INTO books (id, title, author) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE books SET title = ?, author = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM books WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM books";
    private static final String SELECT_BOOK_BY_ID = SELECT_ALL + " WHERE id = ?";
    private static final String SELECT_BOOK_BY_TITLE = SELECT_ALL + " WHERE title = ?";
    private static final String SELECT_BOOK_BY_AUTHOR = SELECT_ALL + " WHERE author = ?";

    private static final int COLUMN_BOOK_ID = 1;
    private static final int COLUMN_TITLE = 2;
    private static final int COLUMN_AUTHOR = 3;

    @Override
    public Optional<Book> find(int id) {
        Optional<Book> result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(SELECT_BOOK_BY_ID);
            query.setInt(1, id);
            try (ResultSet resultSet = query.executeQuery()) {
                result = getOptionalBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Optional<Book> getOptionalBookFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<Book> result = Optional.empty();
        if (resultSet != null) {
            while (resultSet.next()) {
                Book book = buildBook(resultSet);
                result = Optional.of(book);
            }
        }
        return result;
    }

    private Book buildBook(ResultSet resultSet) throws SQLException {
        return new Book.Builder()
                .setId(resultSet.getInt(COLUMN_BOOK_ID))
                .setTitle(resultSet.getString(COLUMN_TITLE))
                .setAuthor(resultSet.getString(COLUMN_AUTHOR)).build();
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return findByColumnValue(SELECT_BOOK_BY_TITLE, title);
    }

    private Optional<Book> findByColumnValue(String sql, String columnValue) {
        Optional<Book> result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(sql);
            query.setString(1, columnValue);
            try (ResultSet resultSet = query.executeQuery()) {
                result = getOptionalBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<Book> findByAuthor(String author) {
        return findByColumnValue(SELECT_BOOK_BY_AUTHOR, author);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (QueryJDBC query = new QueryJDBC()){
            query.createStatement();
            try (ResultSet resultSet = query.executeQuery(SELECT_ALL)) {
                Book book;
                while (resultSet.next()) {
                    book = buildBook(resultSet);
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public int create(Book book) {
        return executeQuery(book, INSERT);
    }

    private int executeQuery(Book book, String sql) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(sql);
            query.setInt(1, book.getId());
            query.setString(2, book.getTitle());
            query.setString(3, book.getAuthor());
            result = query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int update(Book book) {
        return executeQuery(book, UPDATE);
    }

    @Override
    public int delete(int id) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(DELETE);
            query.setInt(1, id);
            query.executeUpdate();
            result = query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
