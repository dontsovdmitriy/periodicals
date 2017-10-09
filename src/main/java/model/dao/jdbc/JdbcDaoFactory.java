package model.dao.jdbc;

import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import model.dao.DaoFactory;
import model.dao.interfaces.*;

import java.sql.*;

public class JdbcDaoFactory extends DaoFactory {

	private static final Logger logger = Logger.getLogger(JdbcDaoFactory.class);
	private static DataSource dataSource;

	public JdbcDaoFactory() {
		try {
			InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/periodicals_db"); 
		} catch (NamingException e) {
			logger.error("Error in looking up the data source: ", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public DaoConnection getConnection() {
		try {
			return new JdbcDaoConnection(dataSource.getConnection());
		} catch (SQLException e) {
			logger.error("Error during the DaoConnection getting: ", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserDao createUserDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcUserDao(sqlConnection);
	}

	@Override
	public PublisherDao createPublisherDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcPublisherDao(sqlConnection);
	}

	@Override
	public PeriodicalCategoryDao createPeriodicalCategoryDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcPeriodicalCategoryDao(sqlConnection);
	}

	@Override
	public PeriodicalDao createPeriodicalDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcPeriodicalDao(sqlConnection);
	}

	@Override
	public SubscriptionDao createSubscriptionDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcSubscriptionDao(sqlConnection);
	}

	@Override
	public InvoiceDao createInvoiceDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcInvoiceDao(sqlConnection);
	}
}
