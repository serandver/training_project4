package com.training.library.dao.connection;

import java.sql.Connection;

public final class ConnectionManager {

	private static volatile ConnectionManager instance;
	private static CustomConnectionPool customConnectionPool;
	private static JndiConnectionPool jndiConnectionPool;

	private ConnectionManager() {
        customConnectionPool = CustomConnectionPool.getInstance();
    }
			
	public static ConnectionManager getInstance() {
		if (instance == null) {
			synchronized (ConnectionManager.class) {
				if (instance == null) {
					instance = new ConnectionManager();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection() {
		return customConnectionPool.getConnection();
	}
	
	public void releaseConnection(Connection connection) {
		customConnectionPool.releaseConnection(connection);
	}

}
