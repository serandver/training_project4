package com.training.library.dao.connection;

import java.sql.Connection;

public final class ConnectionManager {

	private static volatile ConnectionManager connectionManagerInstance;
	private static CustomConnectionPool connectionPool = CustomConnectionPool.getConnectionPoolInstance();
	private static JndiConnectionPool poolContext;
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
		return connectionPool.getConnection();
	}
	
	public void releaseConnection(Connection connection) {
		connectionPool.releaseConnection(connection);
	}

}
