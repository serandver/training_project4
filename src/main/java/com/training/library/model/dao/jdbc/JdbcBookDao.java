package com.training.library.model.dao.jdbc;

import com.training.library.model.connection.QueryJDBC;
import com.training.library.model.entities.Book;
import com.training.library.model.dao.BookDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookDao implements BookDao {

    private static final String SQL_INSERT = "INSERT INTO books (id, title, author) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE books SET title = ?, author = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM books WHERE id = ?";
    private static final String SELECT_FROM_BOOKS = "SELECT * FROM books";
    private static final String SELECT_FROM_BOOKS_BY_ID = "SELECT * FROM books WHERE id = ?";
    private static final String SELECT_FROM_BOOKS_BY_TITLE = "SELECT * FROM books WHERE title = ?";
    private static final String SELECT_FROM_BOOKS_BY_AUTHOR = "SELECT * FROM books WHERE author = ?";

    private static final int COLUMN_ID = 1;
    private static final int COLUMN_TITLE = 2;
    private static final int COLUMN_AUTHOR = 3;

    @Override
    public Optional<Book> find(int id) {
        QueryJDBC query = new QueryJDBC();
        Optional<Book> result = Optional.empty();
        try {
            query.createPreparedStatement(SELECT_FROM_BOOKS_BY_ID);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalBookFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println("=== Failed to create prepared statement ===");
            e.printStackTrace();
        }
        finally {
            query.close();
        }
        return result;
    }

    private Optional<Book> getOptionalBookFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<Book> result = Optional.empty();
        if (resultSet != null) {
            while (resultSet.next()) {
                Book book = buildBook(resultSet);
                result = Optional.of(book);            }
            resultSet.close();
        }
        return result;
    }

    private Book buildBook(ResultSet resultSet) throws SQLException {
        return new Book.Builder()
                .setId(resultSet.getInt(COLUMN_ID))
                .setTitle(resultSet.getString(COLUMN_TITLE))
                .setAuthor(resultSet.getString(COLUMN_AUTHOR)).build();
    }

    @Override
    public List<Book> findAll() {
        QueryJDBC query = new QueryJDBC();
        List<Book> books = new ArrayList<>();
        try {
            query.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_FROM_BOOKS);
            Book book;
            while (resultSet.next()) {
                book = buildBook(resultSet);
                books.add(book);
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed to create statement");
            e.printStackTrace();
        }
        finally {
            query.close();
        }
        return books;
    }

    @Override
    public int create(Book book) {
        QueryJDBC query = new QueryJDBC();
        int result = executeQuery(book, query, SQL_INSERT);
        return result;
    }

    private int executeQuery(Book book, QueryJDBC query, String sql) {
        int result = 0;
        try {
            query.createPreparedStatement(sql);
            query.setInt(1, book.getId());
            query.setString(2, book.getTitle());
            query.setString(3, book.getAuthor());
            result = query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to create prepared statement");
            e.printStackTrace();
        } finally {
            query.close();
        }
        return result;
    }

    @Override
    public int update(Book book) {
        QueryJDBC query = new QueryJDBC();
        int result = executeQuery(book, query, SQL_UPDATE);
        return result;
    }

    @Override
    public int delete(int id) {
        QueryJDBC query = new QueryJDBC();
        int result = 0;
        try {
            query.createPreparedStatement(SQL_DELETE);
            query.setInt(1, id);
            query.executeUpdate();
            result = query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to create prepared statement");
            e.printStackTrace();
        }
        finally {
            query.close();
        }
        return result;
    }
    @Override
    public Optional<Book> findByTitle(String title) {
        QueryJDBC query = new QueryJDBC();
        Optional<Book> result = Optional.empty();
        try {
            query.createPreparedStatement(SELECT_FROM_BOOKS_BY_TITLE);
            query.setString(1, title);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalBookFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println("=== Failed to create prepared statement ===");
            e.printStackTrace();
        }
        finally {
            query.close();
        }
        return result;
    }

    @Override
    public Optional<Book> findByAuthor(String author) {
        QueryJDBC query = new QueryJDBC();
        Optional<Book> result = Optional.empty();
        try {
            query.createPreparedStatement(SELECT_FROM_BOOKS_BY_AUTHOR);
            query.setString(1, author);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalBookFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println("=== Failed to create prepared statement ===");
            e.printStackTrace();
        }
        finally {
            query.close();
        }
        return result;
    }
}
