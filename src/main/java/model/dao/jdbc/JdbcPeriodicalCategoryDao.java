package model.dao.jdbc;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import model.dao.UtilDao;
import model.dao.interfaces.PeriodicalCategoryDao;
import model.entity.periodical.PeriodicalCategory;

public class JdbcPeriodicalCategoryDao implements PeriodicalCategoryDao {

    private static final Logger logger = Logger.getLogger(JdbcPeriodicalCategoryDao.class);

	private static final String INSERT_CATEGORY = "INSERT INTO periodical_categories " + "(category_name) " + "VALUES (?)";
	private static final String IS_CATEGORY_EXISTS = "SELECT COUNT(id) FROM periodical_categories WHERE category_name = ?";
	private static final String SELECT_ALL_CATEGORY = "SELECT * FROM periodical_categories";
	private static final String SELECT_FROM_PERIODICAL_CATEGORY_BY_ID = "SELECT * FROM periodical_categories WHERE id = ?";


	private final boolean connectionShouldBeClosed;
	private Connection connection;

	JdbcPeriodicalCategoryDao(Connection connection) {
		this.connection = connection;
		connectionShouldBeClosed = false;
	}

	public JdbcPeriodicalCategoryDao(Connection connection, boolean connectionShouldBeClosed) {

		this.connectionShouldBeClosed = connectionShouldBeClosed;
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public long add(PeriodicalCategory category) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS )){
			ps.setString(1, category.getCategoryName());
			ps.executeUpdate();

			ResultSet keys =  ps.getGeneratedKeys();
			if( keys.next()){
				return keys.getInt(1);
			} else {
				return -1L;
			}

		} catch (SQLException e) {
			String message = String.format("Exception during add a category with name = %s", category.getCategoryName());
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		//TODO зачем это и по закрывать конекшены	
		//return connectionShouldBeClosed; 
	}

	@Override
	public long update(PeriodicalCategory e) {
		return 0;
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean categoryExistsInDb(String category) {
		try (PreparedStatement ps = connection.prepareStatement(IS_CATEGORY_EXISTS, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, category);

			ResultSet rs = ps.executeQuery();
			return rs.next() && rs.getInt(1) > 0;

		} catch (SQLException e) {
			String message = String.format("Exception during check existing category = %s", category);
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}
	
	@Override
	public List<PeriodicalCategory> findAll() {
		List<PeriodicalCategory> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_CATEGORY)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createPeriodicalCategory(rs));
			}
			
		} catch (SQLException e) {
			String message = String.format("Exception during find all category");
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}
	
	@Override
	public Optional<PeriodicalCategory> find(long id) {
		Optional<PeriodicalCategory> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_PERIODICAL_CATEGORY_BY_ID)) {
			ps.setLong( 1 , id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDao.createPeriodicalCategory(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find  periodical category with id= " + id);
            logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}
}
