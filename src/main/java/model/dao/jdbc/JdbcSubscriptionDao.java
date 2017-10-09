package model.dao.jdbc;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.apache.log4j.Logger;

import model.dao.UtilDao;
import model.dao.interfaces.*;
import model.entity.invoice.Invoice;
import model.entity.subscription.Subscription;
import model.entity.user.User;

public class JdbcSubscriptionDao implements SubscriptionDao {

	private static final Logger logger = Logger.getLogger(JdbcSubscriptionDao.class);

	private static final String INSERT_SUBSCRIPTION = "INSERT INTO subscriptions " + "(user_id, periodical_id, start_date, number_month, address) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_SUBSCRIPTION = "SELECT * FROM subscriptions";
	private static final String SELECT_FROM_SUBSCRIPTION_BY_ID = "SELECT * FROM subscriptions WHERE id = ?";
	private static final String SELECT_FROM_SUBSCRIPTION_BY_USER_ID_AND_INVOICE_STATUS = "SELECT * FROM subscriptions " +
			"WHERE subscriptions.user_id = ? " + 
			"AND EXISTS (SELECT invoices.id FROM invoices WHERE invoices.subscription_id = subscriptions.id AND invoices.status = ?)"; 
	private static final String SELECT_FROM_SUBSCRIPTIONS_BY_USER_ID_DATE_INVOICE_STATUS = "SELECT * FROM subscriptions " +
			"JOIN invoices  ON (subscriptions.id = invoices.subscription_id) " + 
			"WHERE invoices.status = ? " +  
			"AND subscriptions.user_id = ? " +
			"AND start_date <= CURDATE() " + 
			"HAVING (DATE_ADD(start_date, interval number_month month )>= CURDATE())";
	private static final String SELECT_FROM_SUBSCRIPTIONS_BY_DATE_INVOICE_STATUS = "SELECT * FROM subscriptions " +
			"JOIN invoices  ON (subscriptions.id = invoices.subscription_id) " + 
			"WHERE invoices.status = ? " +  
			"AND start_date <= CURDATE() " + 
			"HAVING (DATE_ADD(start_date, interval number_month month )>= CURDATE())";

	private Connection connection;

	public JdbcSubscriptionDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long add(Subscription subscription) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_SUBSCRIPTION, Statement.RETURN_GENERATED_KEYS )){
			ps.setLong(1, subscription.getUser().getId());
			ps.setLong(2, subscription.getPeriodical().getId());
			ps.setDate(3, Date.valueOf(subscription.getStartDate()));
			ps.setInt(4, subscription.getNumberMonth());
			ps.setString(5, subscription.getAddress());

			ps.executeUpdate();

			ResultSet keys =  ps.getGeneratedKeys();
			if( keys.next()){
				return keys.getLong(1);
			} else {
				return -1L;
			}

		} catch (SQLException e) {
			String message = String.format("Exception during add a subscription with periodical = %s", subscription.getPeriodical());
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public long update(Subscription e) {
		return 0;
	}

	@Override
	public List<Subscription> findAll() {
		List<Subscription> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SUBSCRIPTION)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createSubscription(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all subscription");
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}


	@Override
	public Optional<Subscription> find(long id) {
		Optional<Subscription> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_SUBSCRIPTION_BY_ID)) {
			ps.setLong( 1 , id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDao.createSubscription(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find  subscription with id= " + id);
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	@Override
	public List<Subscription> findAllByUserAndInvoiceStatus(User user, Invoice.Status status) {
		List<Subscription> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_SUBSCRIPTION_BY_USER_ID_AND_INVOICE_STATUS)) {

			ps.setString( 1 , status.toString().toLowerCase());
			ps.setLong( 2 , user.getId());
		 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createSubscription(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all subscription");
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public List<Subscription> ActiveSubscriptions(User user) {
		List<Subscription> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_SUBSCRIPTIONS_BY_USER_ID_DATE_INVOICE_STATUS)) {

			ps.setString( 1 , Invoice.Status.PAID.name().toLowerCase());
			ps.setLong( 2 , user.getId());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createSubscription(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find active subscription");
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public List<Subscription> ActiveSubscriptionsAll() {
		List<Subscription> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_SUBSCRIPTIONS_BY_DATE_INVOICE_STATUS)) {

			ps.setString( 1 , Invoice.Status.PAID.name().toLowerCase());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createSubscription(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all active subscription");
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}
}
