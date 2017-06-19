package com.training.library.model.connection;

import java.sql.*;

public class QueryJDBC implements AutoCloseable{

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
    private Statement statement = null;

    private static ConnectionManagerJDBC connectionManager = ConnectionManagerJDBC.getConnectionManagerInstance();

    public QueryJDBC() {
        this.connection = connectionManager.getConnection();
    }

    public void createStatement() throws SQLException {
        this.statement = connection.createStatement();
    }
    public void createPreparedStatement(String query) throws SQLException {
        this.preparedStatement = connection.prepareStatement(query);
    }
	
	public void setString(int key, String value) throws SQLException {
        preparedStatement.setString(key, value);
	}
	
	public void setInt(int key, int value) throws SQLException {
        preparedStatement.setInt(key, value);
	}
	
	public void setBoolean(int key, boolean value) throws SQLException {
        preparedStatement.setBoolean(key, value);
	}
	
	public void setDate(int key, Date date) throws SQLException {
        preparedStatement.setDate(key, date);
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}	
	
	public ResultSet executeQuery() throws SQLException {
		return preparedStatement.executeQuery();
	}
	
	public int executeUpdate() throws SQLException {
		return preparedStatement.executeUpdate();
	}
	
	@Override
	public void close() {
		try {
			if (this.preparedStatement != null) {
				this.preparedStatement.close();
			}
            if (this.statement != null) {
                this.statement.close();
            }
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
		finally {
			connectionManager.releaseConnection(connection);
		}
	}
}
