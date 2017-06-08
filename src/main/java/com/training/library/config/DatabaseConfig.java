package com.training.library.config;

import java.util.ResourceBundle;

public class DatabaseConfig {
    public static final String DATABASE_DRIVER_NAME = "jdbc.driver";
    public static final String DATABASE_URL = "url";
    public static final String DATABASE_POOL_SIZE = "connection.pool.size";
    public static final String DATABASE_USER = "user";
    public static final String DATABASE_PASSWORD = "password";

    private static final String CONFIGURATION = "db";
    private static volatile DatabaseConfig databaseConfigInstance;
    private ResourceBundle resourceBundle;

    public static DatabaseConfig getDatabaseConfigInstance(){
        if (databaseConfigInstance == null) {
            synchronized (DatabaseConfig.class) {
                if (databaseConfigInstance == null) {
                    databaseConfigInstance = new DatabaseConfig();
                    databaseConfigInstance.resourceBundle = ResourceBundle.getBundle(CONFIGURATION);
                }
            }
        }
        return databaseConfigInstance;
    }

    public String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
