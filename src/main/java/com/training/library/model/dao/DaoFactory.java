package com.training.library.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    private static DaoFactory instance;

    public abstract BookDao createBookDao();
    public abstract BookOrderDao createBookOrderDao();
    public abstract UserDao createUserDao();

    public static DaoFactory getInstance(){
        if(instance == null){
            try(InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE)) {
                Properties databaseProperties = new Properties();
                databaseProperties.load(inputStream);
                String factoryClass = databaseProperties.getProperty(DB_FACTORY_CLASS);
                instance = (DaoFactory) Class.forName(factoryClass).newInstance();
            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
}
