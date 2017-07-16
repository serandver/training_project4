package com.training.library.dao.jdbc;

import com.training.library.dao.connection.QueryJDBC;
import com.training.library.exceptions.ServerException;
import com.training.library.model.Book;
import com.training.library.dao.BookDao;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.training.library.controller.utils.LogMessage.*;

public class JdbcBookDao implements BookDao {

    private static final Logger LOGGER = Logger.getLogger(JdbcBookDao.class);

    private static final String INSERT_BOOK_NUMBER = "INSERT INTO book_numbers (book_number) VALUES(?)";
    private static final String INSERT_BOOK = "INSERT INTO books (title, author, book_number_id, book_status) VALUES(?, ?, ?, ?)";
    private static final String SELECT_ALL_BOOKS =
            "SELECT books.book_id, books.title, books.author, book_numbers.book_number, books.book_status " +
            "FROM books " +
            "JOIN book_numbers " +
            "USING (book_number_id)";
    private static final String SELECT_BOOK_BY_ID = SELECT_ALL_BOOKS + " WHERE book_id = ?";
    private static final String SELECT_BOOK_BY_TITLE = SELECT_ALL_BOOKS + " WHERE books.title LIKE CONCAT('%',?,'%') " +
            "GROUP BY books.title, books.author";
    private static final String SELECT_BOOK_BY_AUTHOR = SELECT_ALL_BOOKS + " WHERE books.author LIKE CONCAT('%',?,'%')" +
            "GROUP BY books.title, books.author";
    private static final String SELECT_BOOK_BY_STATUS = SELECT_ALL_BOOKS + " WHERE books.book_status = ?";
    private static final String UPDATE_BOOK =
            "UPDATE books " +
            "JOIN book_numbers USING (book_number_id) " +
            "SET books.title = ?, books.author = ?, book_numbers.book_number = ?, books.book_status = ? " +
            "WHERE books.book_id = ?";
    private static final String DELETE_BOOK =
            "DELETE FROM books, book_numbers " +
            "USING books, book_numbers " +
            "WHERE books.book_number_id = book_numbers.book_number_id " +
            "AND books.book_id = ?";

    private static final int COLUMN_BOOK_ID = 1;
    private static final int COLUMN_TITLE = 2;
    private static final int COLUMN_AUTHOR = 3;
    private static final int COLUMN_BOOK_NUMBER = 4;
    private static final int COLUMN_BOOK_STATUS = 5;


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
            LOGGER.error(BOOKDAO_CREATE_ERROR, e);
            throw new ServerException(e);
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
        query.setString(4, book.getBookStatus().name());
        query.executeUpdate();
        return getGeneratedId(query);
    }

    private int getGeneratedId(QueryJDBC query) throws SQLException {
        int id = -1;
        ResultSet resultSet = query.getGeneratedKeys();
        if (resultSet != null && resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;
    }

    @Override
    public List<Book> findAll() {
        return getListBooksByQuery(SELECT_ALL_BOOKS);
    }

    private List<Book> getListBooksByQuery(String selectAllAvailableBooks) {
        List<Book> books;
        try (QueryJDBC query = new QueryJDBC()) {
            query.createStatement();
            ResultSet resultSet = query.executeQuery(selectAllAvailableBooks);
            books = getAllBooksFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(BOOKDAO_FINDALL_ERROR, e);
            throw new ServerException(e);
        }
        return books;
    }

    private List<Book> getAllBooksFromResultSet(ResultSet resultSet) throws SQLException {
        List<Book> books = new ArrayList<>();
        Book book;
        while (resultSet.next()) {
            book = buildBook(resultSet);
            books.add(book);
        }
        return books;
    }

    private Book buildBook(ResultSet resultSet) throws SQLException {
        return new Book.Builder()
                .setId(resultSet.getInt(COLUMN_BOOK_ID))
                .setTitle(resultSet.getString(COLUMN_TITLE))
                .setAuthor(resultSet.getString(COLUMN_AUTHOR))
                .setInventoryNumber(resultSet.getString(COLUMN_BOOK_NUMBER))
                .setBookStatus(Book.BookStatus.valueOf(resultSet.getString(COLUMN_BOOK_STATUS))).build();
    }

    @Override
    public List<Book> findByStatus(Book.BookStatus status) {
        List<Book> booksByStatus;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(SELECT_BOOK_BY_STATUS);
            query.setString(1, status.name());
            ResultSet resultSet = query.executeQuery();
            booksByStatus = getAllBooksFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(BOOKDAO_FINDBY_STATUS_ERROR, e);
            throw new ServerException(e);
        }
        return booksByStatus;
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
            LOGGER.error(BOOKDAO_FINDBY_ID_ERROR, e);
            throw new ServerException(e);
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

    @Override
    public List<Book> findByTitle(String title) {
        return findByColumnValue(SELECT_BOOK_BY_TITLE, title);
    }

    private List<Book> findByColumnValue(String sql, String columnValue) {
        List<Book> result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(sql);
            query.setString(1, columnValue);
            ResultSet resultSet = query.executeQuery();
            result = getAllBooksFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(BOOKDAO_FINDBY_STRING_ERROR, e);
            throw new ServerException(e);
        }
        return result;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return findByColumnValue(SELECT_BOOK_BY_AUTHOR, author);
    }

    @Override
    public int update(Book book) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(UPDATE_BOOK);
            query.setString(1, book.getTitle());
            query.setString(2, book.getAuthor());
            query.setString(3, book.getInventoryNumber());
            query.setString(4, book.getBookStatus().name());
            query.setInt(5, book.getId());
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(BOOKDAO_UPDATE_ERROR, e);
            throw new ServerException(e);
        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(DELETE_BOOK);
            query.setInt(1, id);
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(BOOKDAO_DELETE_ERROR, e);
            throw new ServerException(e);
        }
        return result;
    }
}
