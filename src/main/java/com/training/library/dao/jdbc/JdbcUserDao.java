package com.training.library.dao.jdbc;

import com.training.library.dao.UserDao;
import com.training.library.exceptions.ServerException;
import com.training.library.model.User;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.training.library.controller.utils.LogMessage.*;

public class JdbcUserDao implements UserDao{

    private static final Logger LOGGER = Logger.getLogger(JdbcUserDao.class);

    private final String SELECT_ALL_USERS =
            "SELECT users.user_id, personal_data.first_name, personal_data.last_name, " +
                "login_data.email, login_data.password, login_data.role_name " +
            "FROM users " +
            "JOIN personal_data USING (personal_data_id) " +
            "JOIN login_data USING (login_data_id)";

    private final String INSERT_USER_LOGIN_DATA = "INSERT INTO login_data (email, password, role_name) VALUES(? , ?, ?)";
    private final String INSERT_USER_PERSONAL_DATA = "INSERT INTO personal_data (first_name, last_name) VALUES(?, ?)";
    private final String INSERT_USER = "INSERT INTO users (login_data_id, personal_data_id) VALUES(?, ?)";
    private final String SELECT_USER_BY_ID = SELECT_ALL_USERS + " WHERE user_id = ?";
    private final String SELECT_USER_BY_LOGIN = SELECT_ALL_USERS + " WHERE email = ?";
    private final String UPDATE_USER =
            "UPDATE users " +
            "JOIN personal_data USING (personal_data_id) " +
            "JOIN login_data USING (login_data_id) " +
            "SET personal_data.first_name = ?, personal_data.last_name = ?, login_data.email = ?, " +
                "login_data.password = ?, login_data.role_name = ? " +
            "WHERE users.user_id = ?";
    private final String DELETE_USER =
            "DELETE FROM users, login_data " +
            "USING users, login_data " +
            "WHERE users.login_data_id = login_data.login_data_id " +
            "AND users.user_id = ?";

    private static final int COLUMN_ID = 1;
    private static final int COLUMN_FIRSTNAME = 2;
    private static final int COLUMN_LASTNAME = 3;
    private static final int COLUMN_LOGIN = 4;
    private static final int COLUMN_PASSWORD = 5;
    private static final int COLUMN_ROLE = 6;

    @Override
    public int create(User user) {
        int generatedUserId = -1;
        try (QueryJDBC query = new QueryJDBC()){
            query.beginTransaction();
            int loginDataId = insertLoginData(user, query);
            int personalDataId = insertPersonalData(user, query);
            if (loginDataId != -1 || personalDataId != -1) {
                generatedUserId = insertUser(query, loginDataId, personalDataId);
            }
            else {
                query.rollbackTransaction();
            }
            user.setId(generatedUserId);
            query.commitTransaction();
        } catch (SQLException e) {
            LOGGER.error(USERDAO_CREATEUSER_ERROR, e);
            throw new ServerException(e);
        }
        return generatedUserId;
    }

    private int insertLoginData(User user, QueryJDBC query) throws SQLException {
        query.createPreparedStatement(INSERT_USER_LOGIN_DATA, Statement.RETURN_GENERATED_KEYS);
        query.setString(1, user.getEmail());
        query.setString(2, user.getPassword());
        query.setString(3, user.getRole().name());
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

    private int insertPersonalData(User user, QueryJDBC query) throws SQLException {
        query.createPreparedStatement(INSERT_USER_PERSONAL_DATA, Statement.RETURN_GENERATED_KEYS);
        query.setString(1, user.getFirstName());
        query.setString(2, user.getLastName());
        query.executeUpdate();
        return getGeneratedId(query);
    }

    private int insertUser(QueryJDBC query, int loginDataId, int personalDataId) throws SQLException {
        query.createPreparedStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, loginDataId);
        query.setInt(2, personalDataId);
        query.executeUpdate();
        return getGeneratedId(query);
    }

    @Override
    public List<User> findAll() {
        List<User> users;
        try (QueryJDBC query = new QueryJDBC()){
            query.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_ALL_USERS);
            users = getAllUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(USERDAO_GETALL_ERROR, e);
            throw new ServerException(e);
        }
        return users;
    }

    private List<User>  getAllUsersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        User user;
        while (resultSet.next()) {
            user = buildUser(resultSet);
            users.add(user);
        }
        return users;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getInt(COLUMN_ID))
                .setFirstName(resultSet.getString(COLUMN_FIRSTNAME))
                .setLastName(resultSet.getString(COLUMN_LASTNAME))
                .setEmail(resultSet.getString(COLUMN_LOGIN))
                .setPassword(resultSet.getString(COLUMN_PASSWORD))
                .setRole(User.Role.valueOf(resultSet.getString(COLUMN_ROLE))).build();
    }

    @Override
    public Optional<User> find(int id) {
        Optional<User> result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(SELECT_USER_BY_ID);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalUserFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(USERDAO_FINDUSER_ERROR, e);
            throw new ServerException(e);
        }
        return result;
    }

    private Optional<User> getOptionalUserFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<User> result = Optional.empty();
        if (resultSet != null) {
            while (resultSet.next()) {
                User user = buildUser(resultSet);
                result = Optional.of(user);
            }
        }
        return result;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(SELECT_USER_BY_LOGIN);
            query.setString(1, email);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalUserFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(USERDAO_FINDBY_EMAIL_ERROR, e);
            throw new ServerException(e);
        }
        return result;
    }

    @Override
    public int update(User user) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(UPDATE_USER);
            query.setString(1, user.getFirstName());
            query.setString(2, user.getLastName());
            query.setString(3, user.getEmail());
            query.setString(4, user.getPassword());
            query.setString(5, user.getRole().name());
            query.setInt(6, user.getId());
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(USERDAO_UPDATEUSER_ERROR, e);
            throw new ServerException(e);
        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(DELETE_USER);
            query.setInt(1, id);
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(USERDAO_DELETEUSER_ERROR, e);
            throw new ServerException(e);
        }
        return result;
    }
}
