package com.training.library.model.dao.jdbc;

import com.training.library.model.connection.QueryJDBC;
import com.training.library.model.dao.UserDao;
import com.training.library.model.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserDao implements UserDao{

    private final String SELECT_ALL =   "SELECT users.user_id, personal_data.first_name, personal_data.last_name, login_data.email, login_data.password, login_data.role_name FROM users " +
            "JOIN personal_data ON users.personal_data_id = personal_data.personal_data_id " +
            "JOIN login_data ON users.login_data_id = login_data.login_data_id";

    private final String INSERT_USER_LOGIN_DATA = "INSERT INTO login_data (email, password, role_name) VALUES(? , ?, ?);";
    private final String INSERT_USER_PERSONAL_DATA = "INSERT INTO personal_data (first_name, last_name) VALUES(?, ?);";
    private final String INSERT_USER = "INSERT INTO users (login_data_id, personal_data_id) VALUES(?, ?);";

    private final String SELECT_USER_BY_ID = SELECT_ALL + " WHERE user_id = ?";

    private final String UPDATE = "UPDATE users SET first_name = ?, last_name = ?, login = ?, password = ?, user_role_id = ? WHERE user_id = ?";
    private final String DELETE = "DELETE FROM users WHERE user_id = ?";
    private final String SELECT_USER_BY_LOGIN = SELECT_ALL + " WHERE email = ?";

    private static final int COLUMN_ID = 1;
    private static final int COLUMN_FIRSTNAME = 2;
    private static final int COLUMN_LASTNAME = 3;
    private static final int COLUMN_LOGIN = 4;
    private static final int COLUMN_PASSWORD = 5;
    private static final int COLUMN_ROLE = 6;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (QueryJDBC query = new QueryJDBC()){
            query.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_ALL);
            getAllUsersFromResultSet(users, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private void getAllUsersFromResultSet(List<User> users, ResultSet resultSet) throws SQLException {
        User user;
        while (resultSet.next()) {
            user = buildUser(resultSet);
            users.add(user);
        }
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
    public int create(User user) {
        int userId = -1;
        ResultSet resultSet;
        try (QueryJDBC query = new QueryJDBC()){
            query.getConnection().setAutoCommit(false);
            query.createPreparedStatement(INSERT_USER_LOGIN_DATA, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, user.getEmail());
            query.setString(2, user.getPassword());
            query.setString(3, user.getRole().name());
            query.executeUpdate();
            resultSet = query.getGeneratedKeys();
            int loginDataId = -1;
            if (resultSet != null && resultSet.next()) {
                loginDataId = resultSet.getInt(1);
            }

            query.createPreparedStatement(INSERT_USER_PERSONAL_DATA, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, user.getFirstName());
            query.setString(2, user.getLastName());
            query.executeUpdate();
            query.getGeneratedKeys();
            int personalDataId  = -1;
            resultSet = query.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                personalDataId = resultSet.getInt(1);
            }

            query.createPreparedStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            if (loginDataId != -1 && personalDataId != -1) {
                query.setInt(1, loginDataId);
                query.setInt(2, personalDataId);
            }
            else {
                throw new RuntimeException();
            }
            query.executeUpdate();
            resultSet = query.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                user.setId(resultSet.getInt(1));
                userId = resultSet.getInt(1);
            }
            query.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
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
            throw new RuntimeException(e);
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
    public Optional<User> findByLogin(String login) {
        Optional<User> result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(SELECT_USER_BY_LOGIN);
            query.setString(1, login);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }





    private int executeQuery(User user, String sql) {
        int result;
        try (QueryJDBC query = new QueryJDBC()){
            query.createPreparedStatement(sql);
            query.setInt(1, user.getId());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getEmail());
            query.setString(5, user.getPassword());
            query.setInt(6, user.getRole().ordinal() + 1);
            result = query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int update(User user) {
        return executeQuery(user, UPDATE);
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
