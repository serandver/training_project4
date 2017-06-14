package com.training.library.model.dao.jdbc;

import com.training.library.model.connection.QueryJDBC;
import com.training.library.model.dao.UserDao;
import com.training.library.model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserDao implements UserDao{

    protected final String SQL_INSERT = "INSERT INTO users (id, first_name, last_name, login, password, user_role_id) VALUES (?, ?, ?, ?, ?, ?)";
    protected final String SQL_SELECT_ALL = "SELECT * FROM users";
    protected final String SQL_UPDATE = "UPDATE users SET first_name = ?, last_name = ?, login = ?, password = ?, user_role_id = ? WHERE id = ?";
    protected final String SQL_DELETE = "DELETE FROM users WHERE id = ?";
    protected final String SQL_SELECT_USER_BY_LOGIN = SQL_SELECT_ALL + " WHERE login = ?";
    protected final String SQL_SELECT_USER_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";

    private static final int COLUMN_ID = 1;
    private static final int COLUMN_FIRSTNAME = 2;
    private static final int COLUMN_LASTNAME = 3;
    private static final int COLUMN_LOGIN = 4;
    private static final int COLUMN_PASSWORD = 5;
    private static final int COLUMN_ROLE = 6;

    @Override
    public Optional<User> find(int id) {
        QueryJDBC query = new QueryJDBC();
        Optional<User> result = Optional.empty();
        try {
            query.createPreparedStatement(SQL_SELECT_USER_BY_ID);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalUserFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println("=== Failed to create prepared statement ===");
            e.printStackTrace();
        }
        finally {
            query.close();
        }
        return result;
    }

    private Optional<User> getOptionalUserFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<User> result = Optional.empty();
        if (resultSet != null) {
            while (resultSet.next()) {
                User user = buildUser(resultSet);
                result = Optional.of(user);            }
            resultSet.close();
        }
        return result;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                    .setId(resultSet.getInt(COLUMN_ID))
                    .setFirstName(resultSet.getString(COLUMN_FIRSTNAME))
                    .setLastName(resultSet.getString(COLUMN_LASTNAME))
                    .setLogin(resultSet.getString(COLUMN_LOGIN))
                    .setPassword(resultSet.getString(COLUMN_PASSWORD))
                    .setRole(User.Role.values()[(resultSet.getInt(COLUMN_ROLE)) - 1]).build();
    }

    @Override
    public List<User> findAll() {
        QueryJDBC query = new QueryJDBC();
        List<User> users = new ArrayList<>();
        try {
            query.createStatement();
            ResultSet resultSet = query.executeQuery(SQL_SELECT_ALL);
            User user;
            while (resultSet.next()) {
                user = buildUser(resultSet);
                System.out.println(user);
                users.add(user);
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
        return users;
    }

    @Override
    public int create(User user) {
        QueryJDBC query = new QueryJDBC();
        int result = executeQuery(user, query, SQL_INSERT);
        return result;
    }

    private int executeQuery(User user, QueryJDBC query, String sql) {
        int result = 0;
        try {
            query.createPreparedStatement(sql);
            query.setInt(1, user.getId());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getLogin());
            query.setString(5, user.getPassword());
            query.setInt(6, user.getRole().ordinal() + 1);
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
    public int update(User user) {
        QueryJDBC query = new QueryJDBC();
        int result = executeQuery(user, query, SQL_UPDATE);
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
    public Optional<User> findByLogin(String login) {
        QueryJDBC query = new QueryJDBC();
        Optional<User> result = Optional.empty();
        try {
            query.createPreparedStatement(SQL_SELECT_USER_BY_LOGIN);
            query.setString(1, login);
            ResultSet resultSet = query.executeQuery();
            result = getOptionalUserFromResultSet(resultSet);
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
