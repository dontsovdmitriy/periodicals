package model.dao;

import java.sql.*;

import org.apache.log4j.Logger;

public class TransactionConnection implements AutoCloseable {

	private static final Logger logger = Logger.getLogger(TransactionConnection.class);

	private Connection connection;
	private boolean inTransaction = false;

	TransactionConnection(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void close() {
		if(inTransaction) {
			rollback();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error("Error during close transaction: ", e);
			throw new RuntimeException(e);
		}
	}

	public void begin() {
		try {
			connection.setAutoCommit(false);
			inTransaction = true;
		} catch (SQLException e) {
			logger.error("Error during begin transaction: ", e);
			throw new RuntimeException(e);
		}
	}

	public void commit() {
		try {
			connection.commit();
			inTransaction = false;
		} catch (SQLException e) {
			logger.error("Error during commit transaction: ", e);
			throw new RuntimeException(e);
		}
	}

	public void rollback() {
		try {
			connection.rollback();
			inTransaction = false;
		} catch (SQLException e) {
			logger.error("Error during rollback transaction: ", e);
			throw new RuntimeException(e);
		}
	}

	Connection getConnection() {
		return connection;
	}
}
