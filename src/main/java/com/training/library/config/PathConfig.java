package com.training.library.config;

import java.util.ResourceBundle;

public class PathConfig {
    public static final String START_PAGE = "START_PAGE";
    public static final String ERROR_PAGE = "ERROR_PAGE";
    public static final String LOGIN_PAGE = "LOGIN_PAGE";
    public static final String READER_HOME_PAGE = "READER_HOME_PAGE";
    public static final String MY_ORDERS_PAGE = "MY_ORDERS_PAGE";
    public static final String LIBRARIAN_HOME_PAGE = "LIBRARIAN_HOME_PAGE";
    public static final String CATALOGUE_PAGE = "CATALOGUE_PAGE";

    private static final String CONFIGURATION = "path";

    private static volatile PathConfig instance;
    private ResourceBundle resourceBundle;

    public static PathConfig getInstance(){
        if (instance == null) {
            synchronized (PathConfig.class) {
                if (instance == null) {
                    instance = new PathConfig();
                    instance.resourceBundle = ResourceBundle.getBundle(CONFIGURATION);
                }
            }
        }
        return instance;
    }

    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}
