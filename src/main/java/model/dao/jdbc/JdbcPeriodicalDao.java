package model.dao.jdbc;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import model.dao.UtilDao;
import model.dao.interfaces.PeriodicalDao;
import model.entity.periodical.Periodical;

public class JdbcPeriodicalDao implements PeriodicalDao {

	private static final Logger logger = Logger.getLogger(JdbcPeriodicalDao.class);

	private static final String INSERT_PERIODICAL = "INSERT INTO periodicals " + "(name, description, cost_per_month, publisher_id, category_id, status) " 
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static final String IS_CATEGORY_EXISTS = "SELECT COUNT(id) FROM periodicals WHERE name = ?";
	private static final String SELECT_ALL_PERIODICAL = "SELECT * FROM periodicals";
	private static final String SELECT_FROM_PERIODICAL_BY_ID = "SELECT * FROM periodicals WHERE id = ?";
	private static final String UPDATE_PERIODICAL_BY_ID = "UPDATE periodicals " +
			"SET name=?, description=?, cost_per_month=?, publisher_id=?, category_id=?, status=? "
			 + "WHERE id=?";

	private Connection connection;

	public JdbcPeriodicalDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long add(Periodical periodical) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_PERIODICAL, Statement.RETURN_GENERATED_KEYS )){
			ps.setString(1, periodical.getName());
			ps.setString(2, periodical.getDescription());
			ps.setLong(3, periodical.getCostPerMonth());
			ps.setLong(4, periodical.getPublisher().getId());
			ps.setLong(5, periodical.getPeriodicalCategory().getId());
			ps.setString(6, periodical.getStatus().toString());

			ps.executeUpdate();

			ResultSet keys =  ps.getGeneratedKeys();
			if( keys.next()){
				return keys.getLong(1);
			} else {
				return -1L;
			}

		} catch (SQLException e) {
			String message = String.format("Exception during add a periodical with name = %s", periodical.getName());
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public long update(Periodical periodical) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_PERIODICAL_BY_ID)){
			
			ps.setString(1, periodical.getName());
			ps.setString(2, periodical.getDescription());
			ps.setLong(3, periodical.getCostPerMonth());
			ps.setLong(4, periodical.getPublisher().getId());
			ps.setLong(5, periodical.getPeriodicalCategory().getId());
			ps.setString(6, periodical.getStatus().toString());
			ps.setLong(7, periodical.getId());

			return ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during update periodical with name = %s", periodical.getName());
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public boolean periodicalNameExistsInDb(String name) {
		try (PreparedStatement ps = connection.prepareStatement(IS_CATEGORY_EXISTS, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			return rs.next() && rs.getLong(1) > 0;

		} catch (SQLException e) {
			String message = String.format("Exception during check periodical name exist id DB with name = %s", name);
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public List<Periodical> findAll() {
		List<Periodical> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PERIODICAL)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createPeriodical(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all category");
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public Optional<Periodical> find(long id) {
		Optional<Periodical> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_PERIODICAL_BY_ID)) {
			ps.setLong( 1 , id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDao.createPeriodical(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find  user with id= " + id);
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}
}
