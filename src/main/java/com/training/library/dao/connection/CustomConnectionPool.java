package com.training.library.dao.connection;

import com.training.library.config.DatabaseConfig;
import com.training.library.exceptions.ServerException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.training.library.controller.utils.LogMessage.*;

public class CustomConnectionPool {

	private static final Logger LOGGER = Logger.getLogger(CustomConnectionPool.class);

	private int connectionCounter = 0;
	private static volatile CustomConnectionPool instance;
	private Connection [] connectionPoolArray;
    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

	private String jdbcDriver = databaseConfig.getProperty(DatabaseConfig.DATABASE_DRIVER_NAME);
	private String jdbcURL = databaseConfig.getProperty(DatabaseConfig.DATABASE_URL);
	private String user = databaseConfig.getProperty(DatabaseConfig.DATABASE_USER);
	private String password = databaseConfig.getProperty(DatabaseConfig.DATABASE_PASSWORD);
	private int connectionPoolSize = Integer.parseInt(databaseConfig.getProperty(DatabaseConfig.DATABASE_POOL_SIZE));
		
	
	private CustomConnectionPool() {
		connectionPoolArray = new Connection[connectionPoolSize];
        try {
            Class.forName(jdbcDriver);
            LOGGER.info(DRIVER_FOUND);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < connectionPoolSize; i++) {
            Connection connection = null;
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
                LOGGER.info(String.format(CONNECTION_ESTABLISHED, (i + 1)));
			} catch (SQLException e) {
                LOGGER.error(String.format(CONNECTION_FAILED, (i + 1)));
                throw new ServerException(e);
			}
			connectionPoolArray[i] = connection;
		}
	}
	
	public static CustomConnectionPool getInstance() {
		if (instance == null) {
			synchronized (CustomConnectionPool.class) {
				if (instance == null) {
					instance = new CustomConnectionPool();
				}
			}
		}
		return instance;
	}
	
	public synchronized Connection getConnection() {
		Connection connection = null;
		while (connectionCounter == connectionPoolSize) {
			try {
				wait();
			} catch (InterruptedException e) {
                LOGGER.error(GET_CONNECTION_FAILED, e);
                throw new ServerException(e);
			}
		}		
		connectionCounter++;
		for (int i = 0; i < connectionPoolSize; i++) {
			if (connectionPoolArray[i] != null) {
				connection = connectionPoolArray[i];
				connectionPoolArray[i] = null;
				break;
			}
		}
		notify();
		return connection;
	}

    public synchronized void releaseConnection(Connection connection) {
		connectionCounter--;
		for (int i = 0; i < connectionPoolSize; i++) {
			if (connectionPoolArray[i] == null) {
				connectionPoolArray[i] = connection;
				break;
			}
		}
	}
		
	synchronized public void closeAll() {
		for (int i = 0; i < connectionPoolSize; i++) {
			if (connectionPoolArray[i] != null) {
				try {
					connectionPoolArray[i].close();
				} catch (SQLException e) {
                    LOGGER.error(String.format(CLOSE_CONNECTION_FAILED, (i + 1)));
                    throw new ServerException(e);
				}
			}
		}
		connectionPoolArray = null;
	}
}
