package com.training.library.model.dao.jdbc;

import com.training.library.model.connection.QueryJDBC;
import com.training.library.model.dao.BookOrderDao;
import com.training.library.model.entities.Book;
import com.training.library.model.entities.BookOrder;
import com.training.library.model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookOrderDao implements BookOrderDao{
    private final String INSERT = "INSERT INTO orders (order_id, user_id, book_id, date_from, reading_place, is_returned) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SELECT_ALL = "SELECT " +
            "                               orders.order_id, orders.date_from, orders.reading_place," +
            "                               orders.is_returned, users.user_id, users.first_name," +
            "                               users.last_name, books.book_id, books.title, books.author " +
            "                           FROM orders" +
                                        "JOIN users ON orders.user_id = users.user_id" +
                                        "JOIN books ON orders.book_id = books.book_id";
    private final String UPDATE = "UPDATE orders SET user_id = ?, book_id = ?, date_from = ?, reading_place = ?, is_returned = ? WHERE order_id = ?";
    private final String DELETE = "DELETE FROM orders WHERE order_id = ?";
    private final String SELECT_ORDER_BY_ID = SELECT_ALL + " WHERE order_id = ?";
    private final String SELECT_ORDER_BY_USER_ID = SELECT_ALL + " WHERE user_id = ?";
    private final String SELECT_ORDER_BY_BOOK_ID = SELECT_ALL + " WHERE book_id = ?";
    private final String SELECT_ORDER_BY_READING_PLACE = SELECT_ALL + " WHERE reading_place = ?";
    private final String SELECT_ORDER_BY_STATUS = SELECT_ALL + " WHERE is_returned = ?";

    private static final int COLUMN_ORDER_ID = 1;
    private static final int COLUMN_DATE_FROM = 2;
    private static final int COLUMN_READING_PLACE = 3;
    private static final int COLUMN_STATUS = 4;
    private static final int COLUMN_USER_ID = 5;
    private static final int COLUMN_FIRSTNAME = 6;
    private static final int COLUMN_LASTNAME = 7;
    private static final int COLUMN_BOOK_ID = 8;
    private static final int COLUMN_TITLE = 9;
    private static final int COLUMN_AUTHOR = 10;


    @Override
    public Optional<BookOrder> find(int id) {
        Optional<BookOrder> result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(SELECT_ORDER_BY_ID);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalBookOrderFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Optional<BookOrder> getOptionalBookOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<BookOrder> result = Optional.empty();
        if (resultSet != null) {
            while (resultSet.next()) {
                BookOrder order = buildBookOrder(resultSet);
                result = Optional.of(order);
            }
        }
        return result;
    }

    private BookOrder buildBookOrder(ResultSet resultSet) throws SQLException {
        User user = buildUser(resultSet);
        Book book = buildBook(resultSet);

        return new BookOrder.Builder()
                .setId(resultSet.getInt(COLUMN_ORDER_ID))
                .setUser(user)
                .setBook(book)
                .setDateFrom(resultSet.getDate(COLUMN_DATE_FROM))
                .setPlace(BookOrder.ReadingPlace.valueOf(resultSet.getString(COLUMN_READING_PLACE)))
                .setReturned(resultSet.getBoolean(COLUMN_STATUS)).build();
    }

    private Book buildBook(ResultSet resultSet) throws SQLException {
        return new Book.Builder()
                    .setId(resultSet.getInt(COLUMN_BOOK_ID))
                    .setTitle(resultSet.getString(COLUMN_TITLE))
                    .setAuthor(resultSet.getString(COLUMN_AUTHOR)).build();
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                    .setId(resultSet.getInt(COLUMN_USER_ID))
                    .setFirstName(resultSet.getString(COLUMN_FIRSTNAME))
                    .setLastName(resultSet.getString(COLUMN_LASTNAME)).build();
    }

    @Override
    public List<BookOrder> findAll() {
        List<BookOrder> orders = new ArrayList<>();
        try (QueryJDBC query = new QueryJDBC()){
            query.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_ALL);
            getAllOrdersFromResultSet(orders, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    private void getAllOrdersFromResultSet(List<BookOrder> orders, ResultSet resultSet) throws SQLException {
        BookOrder order;
        while (resultSet.next()) {
            order = buildBookOrder(resultSet);
            orders.add(order);
        }
    }

    @Override
    public int create(BookOrder bookOrder) {
        return executeQuery(bookOrder, INSERT);
    }

    private int executeQuery(BookOrder bookOrder, String sql) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(sql);
            query.createPreparedStatement(sql);
            query.setInt(1, bookOrder.getId());
            query.setInt(2, bookOrder.getUser().getId());
            query.setInt(3, bookOrder.getBook().getId());
            query.setDate(4, new java.sql.Date(bookOrder.getDateFrom().getTime()));
            query.setString(5, bookOrder.getPlace().name());
            query.setBoolean(6, bookOrder.isReturned());
            result = query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int update(BookOrder bookOrder) {
        return executeQuery(bookOrder, UPDATE);
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

    @Override
    public List<BookOrder> findByUserId(int userId) {
//        return findByColumnValue(SELECT_ORDER_BY_USER_ID, userId);
        return null;
    }

    @Override
    public List<BookOrder> findByBookId(int bookId) {
//        return findByColumnValue(SELECT_ORDER_BY_BOOK_ID, bookId);
        return null;
    }

    @Override
    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place) {
//        return findByColumnValue(SELECT_ORDER_BY_READING_PLACE, place);
        return null;
    }

    @Override
    public List<BookOrder> findByStatus(boolean isReturned) {
//        return findByColumnValue(SELECT_ORDER_BY_STATUS, isReturned);
        return null;
    }

    private Optional<BookOrder> findByColumnValue(String sql, String columnValue) {
    Optional<BookOrder> result;
    try (QueryJDBC query = new QueryJDBC()){
        query.createPreparedStatement(sql);
        query.setString(1, columnValue);
        try (ResultSet resultSet = query.executeQuery()) {
            result = getOptionalBookOrderFromResultSet(resultSet);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return result;
}
}
