package com.training.library.dao.jdbc;

import com.training.library.dao.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcDaoFactory extends DaoFactory {

    private Connection connection;
    private static final String DB_URL = "url";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

    public JdbcDaoFactory() {
        String logProperties = "src/" + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/log4j.properties";
        Path path = Paths.get(logProperties).toAbsolutePath();
        try (InputStream is = new FileInputStream(path.toFile())) {
            Properties properties = new Properties();
            properties.load(is);
        }

        try(InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE)) {
            Properties databaseProperties = new Properties();
            databaseProperties.load(inputStream);
            String factoryClass = databaseProperties.getProperty(DB_FACTORY_CLASS);
            instance = (DaoFactory) Class.forName(factoryClass).newInstance();
        } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Properties databaseProperties = new Properties();
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DaoConnection getConnection() {
        return null;
    }

    @Override
    public BookDao createBookDao() {
        return null;
    }

    @Override
    public BookOrderDao createBookOrderDao() {
        return null;
    }

    @Override
    public UserDao createUserDao() {
        return null;
    }

    @Override
    public BookDao createBookDao(DaoConnection connection) {
        return null;
    }

    @Override
    public BookOrderDao createBookOrderDao(DaoConnection connection) {
        return null;
    }

    @Override
    public UserDao createUserDao(DaoConnection connection) {
        return null;
    }
}
