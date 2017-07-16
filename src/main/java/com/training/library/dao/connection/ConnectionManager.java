package com.training.library.dao.connection;

import java.sql.Connection;

public final class ConnectionManager {

	private static volatile ConnectionManager connectionManagerInstance;
	private static CustomConnectionPool customConnectionPool = CustomConnectionPool.getInstance();
	private static JndiConnectionPool jndiConnectionPool;
	private ConnectionManager() {
	}
			
	public static ConnectionManager getConnectionManagerInstance() {
		if (connectionManagerInstance == null) {
			synchronized (ConnectionManager.class) {
				if (connectionManagerInstance == null) {
					connectionManagerInstance = new ConnectionManager();
				}
			}
		}
		return connectionManagerInstance;
	}
	
	public Connection getConnection() {
		return customConnectionPool.getConnection();
	}
	
	public void releaseConnection(Connection connection) {
		customConnectionPool.releaseConnection(connection);
	}

}
