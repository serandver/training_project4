package com.training.library.dao.jdbc;

import com.training.library.dao.BookOrderDao;
import com.training.library.exceptions.ServerException;
import com.training.library.model.Book;
import com.training.library.model.BookOrder;
import com.training.library.model.User;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.training.library.controller.utils.LogMessage.*;

public class JdbcBookOrderDao implements BookOrderDao{

    private static final Logger LOGGER = Logger.getLogger(JdbcBookOrderDao.class);

    private final String INSERT_ORDER =
            "INSERT INTO orders (user_id, book_id, date_receive, date_return, reading_place, order_status) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private final String SELECT_ALL_ORDERS =
            "SELECT orders.order_id, user_inf.user_id, user_inf.first_name, user_inf.last_name, " +
                "book_inf.book_id, book_inf.title, book_inf.author, book_inf.book_number, " +
                "orders.date_receive, orders.date_return, orders.reading_place, orders.order_status " +
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
            "USING (book_id) ";
    private final String UPDATE_ORDER = "UPDATE orders SET user_id = ?, book_id = ?, date_receive = ?, date_return = ?, " +
            "reading_place = ?, order_status = ? WHERE order_id = ?";
    private final String SELECT_ORDER_BY_ID = SELECT_ALL_ORDERS + " WHERE order_id = ?";
    private final String SELECT_ORDER_BY_USER_ID = SELECT_ALL_ORDERS + " WHERE user_id = ?";
    private final String SELECT_ORDER_BY_BOOK_ID = SELECT_ALL_ORDERS + " WHERE book_id = ?";
    private final String SELECT_ORDER_BY_READING_PLACE = SELECT_ALL_ORDERS + " WHERE reading_place = ?";
    private final String SELECT_ORDER_BY_DATE_OF_RECEIVE = SELECT_ALL_ORDERS + " WHERE orders.date_receive = ?";
    private final String SELECT_ORDER_BY_DATE_OF_RETURN = SELECT_ALL_ORDERS + " WHERE orders.date_return = ?";
    private final String SELECT_ORDER_BY_STATUS = SELECT_ALL_ORDERS + " WHERE orders.order_status = ?";
    private final String SELECT_NOT_RETURNED_BOOKS = SELECT_ALL_ORDERS + " WHERE orders.date_return IS NULL";
    private final String DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";

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
    private static final int COLUMN_ORDER_STATUS = 12;

    @Override
    public int create(BookOrder bookOrder) {
        int generatedBookOrderId;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(INSERT_ORDER);
            query.setInt(1, bookOrder.getUser().getId());
            query.setInt(2, bookOrder.getBook().getId());
            query.setTimestamp(3, getTimeStampOfDate(bookOrder, bookOrder.getDateOfReceive()));
            query.setTimestamp(4, getTimeStampOfDate(bookOrder, bookOrder.getDateOfReturn()));
            query.setString(5, bookOrder.getPlace().name());
            query.setString(6, bookOrder.getOrderStatus().name());
            query.executeUpdate();
            generatedBookOrderId = getGeneratedId(query);
            bookOrder.setId(generatedBookOrderId);
        } catch (SQLException e) {
            LOGGER.error(ORDERDAO_CREATE_ERROR, e);
            throw new ServerException(e);        }
        return generatedBookOrderId;
    }

    private Timestamp getTimeStampOfDate(BookOrder bookOrder, Date date) throws SQLException {
        Timestamp timestamp = null;
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
            LOGGER.error(ORDERDAO_FINDALL_ERROR, e);
            throw new ServerException(e);
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
                .setPlace(BookOrder.ReadingPlace.valueOf(resultSet.getString(COLUMN_READING_PLACE)))
                .setOrderStatus(BookOrder.OrderStatus.valueOf(resultSet.getString(COLUMN_ORDER_STATUS))).build();
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
    public int update(BookOrder bookOrder) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(UPDATE_ORDER);
            query.setInt(1, bookOrder.getUser().getId());
            query.setInt(2, bookOrder.getBook().getId());
            query.setTimestamp(3, new Timestamp(bookOrder.getDateOfReceive().getTime()));
            query.setTimestamp(4, getTimeStampOfDate(bookOrder, bookOrder.getDateOfReturn()));
            query.setString(5, bookOrder.getPlace().name());
            query.setString(6, bookOrder.getOrderStatus().name());
            query.setInt(7, bookOrder.getId());
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(ORDERDAO_UPDATE_ERROR, e);
            throw new ServerException(e);
        }
        return result;
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
            LOGGER.error(ORDERDAO_FINDBY_ERROR, e);
            throw new ServerException(e);
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

    @Override
    public List<BookOrder> findByUserId(int userId) {
        return getBookOrdersByIntColumn(userId, SELECT_ORDER_BY_USER_ID);
    }

    private List<BookOrder> getBookOrdersByIntColumn(int id, String sql) {
        List<BookOrder> ordersByBookId;
        try (QueryJDBC query = new QueryJDBC()) {
            query.createPreparedStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            ordersByBookId = getAllOrdersFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(ORDERDAO_FINDBY_ERROR, e);
            throw new ServerException(e);
        }
        return ordersByBookId;
    }

    @Override
    public List<BookOrder> findByBookId(int bookId) {
        return getBookOrdersByIntColumn(bookId, SELECT_ORDER_BY_BOOK_ID);
    }

    @Override
    public List<BookOrder> findByReadingPlace(BookOrder.ReadingPlace place) {
        return getBookOrderByEnumColumn(place, SELECT_ORDER_BY_READING_PLACE);
    }

    private List<BookOrder> getBookOrderByEnumColumn(Enum enumValue, String sql) {
        List<BookOrder> ordersByEnumValue;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(sql);
            query.setString(1, enumValue.name());
            ResultSet resultSet = query.executeQuery();
            ordersByEnumValue = getAllOrdersFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(ORDERDAO_FINDBY_ERROR, e);
            throw new ServerException(e);
        }
        return ordersByEnumValue;
    }

    @Override
    public List<BookOrder> findByOrderStatus(BookOrder.OrderStatus orderStatus) {
        return getBookOrderByEnumColumn(orderStatus, SELECT_ORDER_BY_STATUS);
    }

    @Override
    public List<BookOrder> findByDateOfReceive(Date date) {
        return getBookOrdersByDate(SELECT_ORDER_BY_DATE_OF_RECEIVE, date);
    }

    private List<BookOrder> getBookOrdersByDate(String sql, Date date) {
        List<BookOrder> ordersByDateOfReceive;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(sql);
            query.setTimestamp(1, new Timestamp(date.getTime()));
            ResultSet resultSet = query.executeQuery();
            ordersByDateOfReceive = getAllOrdersFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(ORDERDAO_FINDBY_ERROR, e);
            throw new ServerException(e);
        }
        return ordersByDateOfReceive;
    }

    @Override
    public List<BookOrder> findByDateOfReturn(Date date) {
        List<BookOrder> ordersByDateOfReturn;
        if (date != null) {
            ordersByDateOfReturn = getBookOrdersByDate(SELECT_ORDER_BY_DATE_OF_RETURN, date);
        } else {
            try (QueryJDBC query = new QueryJDBC()){
                query.createPreparedStatement(SELECT_NOT_RETURNED_BOOKS);
                ResultSet resultSet = query.executeQuery();
                ordersByDateOfReturn = getAllOrdersFromResultSet(resultSet);
            } catch (SQLException e) {
                LOGGER.error(ORDERDAO_FINDBY_ERROR, e);
                throw new ServerException(e);
            }
        }
        return ordersByDateOfReturn;
    }

    @Override
    public int delete(int id) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(DELETE_ORDER);
            query.setInt(1, id);
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(ORDERDAO_DELETE_ERROR, e);
            throw new ServerException(e);
        }
        return result;
    }
}
