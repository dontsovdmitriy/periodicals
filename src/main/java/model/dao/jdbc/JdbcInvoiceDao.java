package model.dao.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import model.dao.UtilDao;
import model.dao.interfaces.*;
import model.entity.invoice.Invoice;
import model.entity.invoice.Invoice.Status;
import model.entity.subscription.Subscription;
import model.entity.user.User;

public class JdbcInvoiceDao implements InvoiceDao{

	private static final Logger logger = Logger.getLogger(JdbcInvoiceDao.class);

	private static final String INSERT_INVOICE = "INSERT INTO invoices " + "(subscription_id, cost, creation_date, status) "
			+ "VALUES (?, ?, ?, ?)";
	private static final String UPDATE_INVOICE_BY_ID = "UPDATE invoices " +
			"SET subscription_id=?, cost=?, creation_date=?, payment_date=?, status=?  WHERE id=?";
	private static final String SELECT_FROM_INVOICE_BY_ID = "SELECT * FROM invoices WHERE id = ?";
	private static final String SELECT_FROM_INVOICE_BY_SUBSCRIPTION_ID = "SELECT * FROM invoices WHERE subscription_id = ?";
	private static final String SELECT_FROM_INVOICE_BY_USER_ID_AND_INVOICE_STATUS = "SELECT * FROM invoices " +
			"JOIN subscriptions  ON (invoices.subscription_id = subscriptions.id) " +
			"WHERE invoices.status = ? AND subscriptions.user_id = ?";

	private Connection connection;

	public JdbcInvoiceDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long add(Invoice invoice) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_INVOICE, Statement.RETURN_GENERATED_KEYS)){
			ps.setLong(1, invoice.getSubscription().getId());
			ps.setLong(2, invoice.getCost());
			ps.setDate(3,  Date.valueOf(invoice.getCreationDate()));
			ps.setString(4, invoice.getStatus().toString());

			ps.executeUpdate();

			ResultSet keys =  ps.getGeneratedKeys();
			if( keys.next()){
				return keys.getLong(1);
			} else {
				return -1L;
			}
		} catch (SQLException e) {
			String message = String.format("Exception during add a invoice with subscription id= %s", invoice.getSubscription().getId());
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public List<Invoice> findAll() {
		return null;
	}

	@Override
	public Optional<Invoice> find(long id) {
		Optional<Invoice> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_INVOICE_BY_ID)) {
			ps.setLong( 1 , id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDao.createInvoice(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find  invoice with id= " + id);
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	@Override
	public long update(Invoice invoice) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_INVOICE_BY_ID)){

			ps.setLong(1, invoice.getSubscription().getId());
			ps.setLong(2, invoice.getCost());
			ps.setDate(3, Date.valueOf(invoice.getCreationDate()));
			ps.setDate(4, Date.valueOf(invoice.getPaymentDate()));
			ps.setString(5, invoice.getStatus().toString().toLowerCase());
			ps.setLong(6, invoice.getId());

			return ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during update ivoice with id = %s", invoice.getId());
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public Optional<Invoice> findBySubscription(Subscription subscription) {
		Optional<Invoice> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_INVOICE_BY_SUBSCRIPTION_ID)) {
			ps.setLong( 1 , subscription.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDao.createInvoice(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find invoice with subscription id= " + subscription.getId());
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	@Override
	public List<Invoice> findAllByUserAndInvoiceStatus(User user, Status status) {
		List<Invoice> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_INVOICE_BY_USER_ID_AND_INVOICE_STATUS)) {

			ps.setString( 1 , status.toString().toLowerCase());
			ps.setLong( 2 , user.getId());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createInvoice(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find invoices by user.id and status");
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}
}
