package com.training.library.model.dao.jdbc;

import com.training.library.model.connection.QueryJDBC;
import com.training.library.model.dao.BookOrderDao;
import com.training.library.model.entities.Book;
import com.training.library.model.entities.BookOrder;
import com.training.library.model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JdbcBookOrderDao implements BookOrderDao{
    private final String INSERT_ORDER =
            "INSERT INTO orders (user_id, book_id, date_receive, date_return, reading_place) " +
            "VALUES (?, ?, ?, ?, ?);";
    private final String SELECT_ALL_ORDERS =
            "SELECT orders.order_id, user_inf.user_id, user_inf.first_name, user_inf.last_name, " +
            "book_inf.book_id, book_inf.title, book_inf.author, book_inf.book_number, " +
            "orders.date_receive, orders.date_return, orders.reading_place " +
            "FROM orders " +
            "JOIN " +
            "(SELECT users.user_id, personal_data.first_name, personal_data.last_name " +
            "FROM users " +
            "JOIN personal_data " +
            "USING (personal_data_id)) AS user_inf " +
            "USING (user_id) " +
            "JOIN " +
            "(SELECT books.book_id, books.title, books.author, book_numbers.book_number " +
            "FROM books " +
            "JOIN book_numbers " +
            "USING (book_number_id)) AS book_inf " +
            "USING (book_id)";
    private final String UPDATE_ORDER = "UPDATE orders SET user_id = ?, book_id = ?, date_from = ?, reading_place = ?, is_returned = ? WHERE order_id = ?";
    private final String DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";
    private final String SELECT_ORDER_BY_ID = SELECT_ALL_ORDERS + " WHERE order_id = ?";
    private final String SELECT_ORDER_BY_USER_ID = SELECT_ALL_ORDERS + " WHERE user_id = ?";
    private final String SELECT_ORDER_BY_BOOK_ID = SELECT_ALL_ORDERS + " WHERE book_id = ?";
    private final String SELECT_ORDER_BY_READING_PLACE = SELECT_ALL_ORDERS + " WHERE reading_place = ?";
    private final String SELECT_ORDER_BY_STATUS = SELECT_ALL_ORDERS + " WHERE is_returned = ?";

    private static final int COLUMN_ORDER_ID = 1;
    private static final int COLUMN_USER_ID = 2;
    private static final int COLUMN_FIRSTNAME = 3;
    private static final int COLUMN_LASTNAME = 4;
    private static final int COLUMN_BOOK_ID = 5;
    private static final int COLUMN_TITLE = 6;
    private static final int COLUMN_AUTHOR = 7;
    private static final int COLUMN_BOOK_NUMBER = 8;
    private static final int COLUMN_DATE_RECEIVE = 9;
    private static final int COLUMN_DATE_RETURN = 10;
    private static final int COLUMN_READING_PLACE = 11;

    @Override
    public int create(BookOrder bookOrder) {
        int generatedBookOrderId;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(INSERT_ORDER);
            query.setInt(1, bookOrder.getUser().getId());
            query.setInt(2, bookOrder.getBook().getId());
            query.setTimestamp(3, new Timestamp(bookOrder.getDateOfReceive().getTime()));
            query.setTimestamp(4, getTimeStampOfReturnDate(bookOrder));
            query.setString(5, bookOrder.getPlace().name());
            query.executeUpdate();
            generatedBookOrderId = getGeneratedId(query);
            bookOrder.setId(generatedBookOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedBookOrderId;
    }

    private Timestamp getTimeStampOfReturnDate(BookOrder bookOrder) throws SQLException {
        Timestamp timestamp = null;
        Date date = bookOrder.getDateOfReturn();
        if (date != null) {
            timestamp = new Timestamp(date.getTime());
        }
        return timestamp;
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
    public List<BookOrder> findAll() {
        List<BookOrder> orders;
        try (QueryJDBC query = new QueryJDBC()){
            query.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_ALL_ORDERS);
            orders = getAllOrdersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    private List<BookOrder> getAllOrdersFromResultSet(ResultSet resultSet) throws SQLException {
        List<BookOrder> orders = new ArrayList<>();
        BookOrder order;
        while (resultSet.next()) {
            order = buildBookOrder(resultSet);
            orders.add(order);
        }
        return orders;
    }

    private BookOrder buildBookOrder(ResultSet resultSet) throws SQLException {
        User user = buildUser(resultSet);
        Book book = buildBook(resultSet);

        return new BookOrder.Builder()
                .setId(resultSet.getInt(COLUMN_ORDER_ID))
                .setUser(user)
                .setBook(book)
                .setDateOfReceive(getDateFromResultSet(resultSet, COLUMN_DATE_RECEIVE))
                .setDateOfReturn(getDateFromResultSet(resultSet, COLUMN_DATE_RETURN))
                .setPlace(BookOrder.ReadingPlace.valueOf(resultSet.getString(COLUMN_READING_PLACE))).build();
    }

    private Date getDateFromResultSet(ResultSet resultSet, int columnNumber) throws SQLException {
        java.util.Date dateUtil = null;
        java.sql.Date dateSql = resultSet.getDate(columnNumber);
        if(dateSql != null) {
            dateUtil = new Date(dateSql.getTime());
        }
        return dateUtil;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getInt(COLUMN_USER_ID))
                .setFirstName(resultSet.getString(COLUMN_FIRSTNAME))
                .setLastName(resultSet.getString(COLUMN_LASTNAME)).build();
    }

    private Book buildBook(ResultSet resultSet) throws SQLException {
        return new Book.Builder()
                .setId(resultSet.getInt(COLUMN_BOOK_ID))
                .setTitle(resultSet.getString(COLUMN_TITLE))
                .setAuthor(resultSet.getString(COLUMN_AUTHOR))
                .setInventoryNumber(resultSet.getString(COLUMN_BOOK_NUMBER)).build();
    }


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





    private int executeQuery(BookOrder bookOrder, String sql) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(sql);
            query.createPreparedStatement(sql);
            query.setInt(1, bookOrder.getId());
            query.setInt(2, bookOrder.getUser().getId());
            query.setInt(3, bookOrder.getBook().getId());
//            query.setDate(4, new java.sql.Date(bookOrder.getDateOfReceive().getTime()));
            query.setString(5, bookOrder.getPlace().name());
//            query.setBoolean(6, bookOrder.isReturned());
            result = query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int update(BookOrder bookOrder) {
        return executeQuery(bookOrder, UPDATE_ORDER);
    }

    @Override
    public int delete(int id) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(DELETE_ORDER);
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
