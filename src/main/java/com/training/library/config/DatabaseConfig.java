package com.training.library.config;

import java.util.ResourceBundle;

public class DatabaseConfig {
    public static final String DATABASE_DRIVER_NAME = "jdbc.driver";
    public static final String DATABASE_URL = "url";
    public static final String DATABASE_POOL_SIZE = "connection.pool.size";
    public static final String DATABASE_USER = "user";
    public static final String DATABASE_PASSWORD = "password";

    private static final String CONFIGURATION = "db";
    private static volatile DatabaseConfig instance;
    private ResourceBundle resourceBundle;

    public static DatabaseConfig getInstance(){
        if (instance == null) {
            synchronized (DatabaseConfig.class) {
                if (instance == null) {
                    instance = new DatabaseConfig();
                    instance.resourceBundle = ResourceBundle.getBundle(CONFIGURATION);
                }
            }
        }
        return instance;
    }

    public String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
