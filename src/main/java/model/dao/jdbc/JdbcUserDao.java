package model.dao.jdbc;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import model.dao.UtilDao;
import model.dao.interfaces.*;
import model.entity.user.User;

public class JdbcUserDao implements UserDao {
	
    private static final Logger logger = Logger.getLogger(JdbcUserDao.class);

	private static final String INSERT_USER = "INSERT INTO users " +
			"(name, surname, mobile_phone, email, type, login, password) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String IS_EMAIL_EXISTS = "SELECT COUNT(id) FROM users WHERE email = ?";
	private static final String IS_LOGIN_EXISTS = "SELECT COUNT(id) FROM users WHERE login = ?";
	private static final String SELECT_FROM_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String SELECT_FROM_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

	private Connection connection;

	JdbcUserDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long add(User user) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS )){
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getMobilePhone());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getType().toString());
			ps.setString(6, user.getLogin());
			ps.setString(7, UtilDao.getPasswordHash(user.getPassword()));

			ps.executeUpdate();

			ResultSet keys =  ps.getGeneratedKeys();
			if( keys.next()){
				return keys.getLong(1);
			} else {
				return -1L;
			}

		} catch (SQLException e) {
			String message = String.format("Exception during add a user with email = %s", user.getEmail());
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public boolean emailExistsInDb(String email) {
		try (PreparedStatement ps = connection.prepareStatement(IS_EMAIL_EXISTS, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			return rs.next() && rs.getLong(1) > 0;

		} catch (SQLException e) {
			String message = String.format("Exception during find email = %s", email);
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public long update(User e) {
		return 0;
	}

	@Override
	public boolean loginExistsInDb(String login) {
		try (PreparedStatement ps = connection.prepareStatement(IS_LOGIN_EXISTS, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, login);

			ResultSet rs = ps.executeQuery();
			return rs.next() && rs.getLong(1) > 0;

		} catch (SQLException e) {
			String message = String.format("Exception during find login = %s", login);
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}
	

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<User> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_USER_BY_EMAIL)) {
			ps.setString(1 , email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDao.createUser(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find  user with email= " + email);
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	@Override
	public Optional<User> find(long id) {
		Optional<User>result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_USER_BY_ID)) {
			ps.setLong( 1 , id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDao.createUser(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find  user with id= " + id);
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}
}



