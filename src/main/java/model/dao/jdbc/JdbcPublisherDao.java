package model.dao.jdbc;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import model.dao.UtilDao;
import model.dao.interfaces.PublisherDao;
import model.entity.periodical.Publisher;

public class JdbcPublisherDao implements PublisherDao {
	
    private static final Logger logger = Logger.getLogger(JdbcPublisherDao.class);

	private static final String INSERT_PUBLISHER = "INSERT INTO publishers " + "(publisher) " + "VALUES (?)";
	private static final String IS_PUBLISHER_EXISTS = "SELECT COUNT(id) FROM publishers WHERE publisher = ?";
	private static final String SELECT_ALL_PUBLISHERS = "SELECT * FROM publishers";
	private static final String SELECT_FROM_PUBLISHER_BY_ID = "SELECT * FROM publishers WHERE id = ?";

	private final boolean connectionShouldBeClosed;
	private Connection connection;

	JdbcPublisherDao(Connection connection) {
		this.connection = connection;
		connectionShouldBeClosed = false;
	}

	public JdbcPublisherDao(Connection connection, boolean connectionShouldBeClosed) {

		this.connectionShouldBeClosed = connectionShouldBeClosed;
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Publisher> findAll() {
		List<Publisher> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PUBLISHERS)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createPublisher(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find  all publisher");
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public long add(Publisher publisher) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_PUBLISHER, Statement.RETURN_GENERATED_KEYS )){
			ps.setString(1, publisher.getPublisher());
			ps.executeUpdate();

			ResultSet keys =  ps.getGeneratedKeys();
			if( keys.next()){
				return keys.getLong(1);
			} else {
				return -1L;
			}

		} catch (SQLException e) {
			String message = String.format("Exception during add a publisher with name = %s", publisher.getPublisher());
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public long update(Publisher e) {
		return 0;
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean publisherExistsInDb(String publisher) {
		try (PreparedStatement ps = connection.prepareStatement(IS_PUBLISHER_EXISTS, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, publisher);

			ResultSet rs = ps.executeQuery();
			return rs.next() && rs.getLong(1) > 0;

		} catch (SQLException e) {
			String message = String.format("Exception during find publisher = %s", publisher);
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public Optional<Publisher> find(long id) {
		Optional<Publisher> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_PUBLISHER_BY_ID)) {
			ps.setLong( 1 , id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDao.createPublisher(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find  publisher with id= " + id);
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}
}
