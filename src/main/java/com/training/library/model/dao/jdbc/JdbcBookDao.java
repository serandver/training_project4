package com.training.library.model.dao.jdbc;

import com.training.library.model.connection.QueryJDBC;
import com.training.library.model.entities.Book;
import com.training.library.model.dao.BookDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookDao implements BookDao {

    private static final String INSERT_BOOK_NUMBER = "INSERT INTO book_numbers (book_number) VALUES(?)";
    private static final String INSERT_BOOK = "INSERT INTO books (title, author, book_number_id) VALUES(?, ?, ?)";


    private static final String UPDATE = "UPDATE books SET title = ?, author = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM books WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM books";
    private static final String SELECT_BOOK_BY_ID = SELECT_ALL + " WHERE id = ?";
    private static final String SELECT_BOOK_BY_TITLE = SELECT_ALL + " WHERE title = ?";
    private static final String SELECT_BOOK_BY_AUTHOR = SELECT_ALL + " WHERE author = ?";

    private static final int COLUMN_BOOK_ID = 1;
    private static final int COLUMN_TITLE = 2;
    private static final int COLUMN_AUTHOR = 3;
    private static final int COLUMN_BOOK_NUMBER = 4;


    @Override
    public int create(Book book) {
        int generatedBookId = -1;
        try (QueryJDBC query = new QueryJDBC()){
            query.beginTransaction();
            int bookNumberId = insertBookNumber(book, query);
            if (bookNumberId != -1) {
                generatedBookId = insertBook(query, book, bookNumberId);
            }
            else {
                query.rollbackTransaction();
            }
            book.setId(generatedBookId);
            query.commitTransaction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedBookId;
    }

    private int insertBookNumber(Book book, QueryJDBC query) throws SQLException {
        query.createPreparedStatement(INSERT_BOOK_NUMBER, Statement.RETURN_GENERATED_KEYS);
        query.setString(1, book.getInventoryNumber());
        query.executeUpdate();
        return getGeneratedId(query);
    }

    private int insertBook(QueryJDBC query, Book book, int bookNumberId) throws SQLException{
        query.createPreparedStatement(INSERT_BOOK, Statement.RETURN_GENERATED_KEYS);
        query.setString(1, book.getTitle());
        query.setString(2, book.getAuthor());
        query.setInt(3, bookNumberId);
        query.executeUpdate();
        return getGeneratedId(query);
    }

    private int getGeneratedId(QueryJDBC query) throws SQLException {
        int id = -1;
        ResultSet resultSet;
        resultSet = query.getGeneratedKeys();
        if (resultSet != null && resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;
    }


    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (QueryJDBC query = new QueryJDBC()){
            query.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_ALL);
            getAllBooksFromResultSet(books, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    private void getAllBooksFromResultSet(List<Book> books, ResultSet resultSet) throws SQLException {
        Book book;
        while (resultSet.next()) {
            book = buildBook(resultSet);
            books.add(book);
        }
    }

    @Override
    public Optional<Book> find(int id) {
        Optional<Book> result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(SELECT_BOOK_BY_ID);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalBookFromResultSet(resultSet);
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
            ResultSet resultSet = query.executeQuery();
            result = getOptionalBookFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<Book> findByAuthor(String author) {
        return findByColumnValue(SELECT_BOOK_BY_AUTHOR, author);
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
            result = query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
