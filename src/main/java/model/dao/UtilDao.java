package model.dao;

import java.security.*;
import java.sql.*;
import java.time.LocalDate;

import org.apache.log4j.Logger;

import model.entity.invoice.Invoice;
import model.entity.periodical.*;
import model.entity.subscription.Subscription;
import model.entity.user.User;

public final class UtilDao {

	private static final Logger logger = Logger.getLogger(UtilDao.class);

	private UtilDao() {
	}

	/**
	 * Creates a new periodical using the data from the result set.
	 */
	public static Periodical createPeriodical(ResultSet resultSet)  {
		try {
			Publisher.Builder publisherBuilder = new Publisher.Builder();
			publisherBuilder.setId(resultSet.getLong("publisher_id"));

			PeriodicalCategory.Builder periodicalCategory = new PeriodicalCategory.Builder();
			periodicalCategory.setId(resultSet.getLong("category_id"));

			return new Periodical.Builder()
					.setId(resultSet.getInt("id"))
					.setName(resultSet.getString("name"))
					.setDescription(resultSet.getString("description"))
					.setCostPerMonth(resultSet.getLong("cost_per_month"))
					.setPublisher(publisherBuilder.build())
					.setPeriodicalCategory(periodicalCategory.build())
					.setStatus(Periodical.Status.valueOf(resultSet.getString("status").toUpperCase())) 
					.build();

		} catch (SQLException e) {
			String message = String.format("Exception during create periodical category entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Creates a new periodical category using the data from the result set.
	 */
	public static PeriodicalCategory createPeriodicalCategory(ResultSet resultSet)  {
		try {
			return new PeriodicalCategory.Builder()
					.setId(resultSet.getLong("id"))
					.setCategoryName(resultSet.getString("category_name"))
					.build();
		} catch (SQLException e) {
			String message = String.format("Exception during create periodical category entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Creates a new publisher using the data from the result set.
	 */
	public static Publisher createPublisher(ResultSet resultSet)  {
		try {
			return new Publisher.Builder()
					.setId(resultSet.getLong("id"))
					.setPublisher(resultSet.getString("publisher"))
					.build();
		} catch (SQLException e) {
			String message = String.format("Exception during create publisher entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Creates a new subscription using the data from the result set.
	 */
	public static Subscription createSubscription(ResultSet resultSet)  {
		try {

			User.Builder userBuilder = new User.Builder();
			userBuilder.setId(resultSet.getLong("user_id"));

			Periodical.Builder periodicalBuilder = new Periodical.Builder();
			periodicalBuilder.setId(resultSet.getLong("periodical_id"));

			return new Subscription.Builder()
					.setId(resultSet.getLong("id"))
					.setUser(userBuilder.build())
					.setPeriodical(periodicalBuilder.build())
					.setStartDate(LocalDate.parse(resultSet.getString("start_date")))
					.setNumberMonth(resultSet.getInt("number_month"))
					.setAddress(resultSet.getString("address"))
					.build();

		} catch (SQLException e) {
			String message = String.format("Exception during create subscription entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Creates a new user using the data from the result set.
	 */
	public static User createUser(ResultSet resultSet)  {
		try {
			return new User.Builder()
					.setId(resultSet.getLong("id"))
					.setName(resultSet.getString("name"))
					.setSurname(resultSet.getString("surname"))
					.setMobilePhone(resultSet.getString("mobile_phone"))
					.setEmail(resultSet.getString("email"))
					.setType(User.Type.valueOf(resultSet.getString("type").toUpperCase()))
					.setLogin(resultSet.getString("login"))
					.setPassword(resultSet.getString("password"))
					.build();

		} catch (SQLException e) {
			String message = String.format("Exception during create publisher user");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Get the password hash using the SHA-256 encoding
	 */
	public static String getPasswordHash(String password) {
		String result = null;
		try {
			MessageDigest ms = MessageDigest.getInstance("SHA-256");
			result = new String(ms.digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			String message = String.format("Exception during get password hash");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	/**
	 * Check the user entered password using a hash password from the database
	 */
	public static boolean checkPassword(String passwordInput, String storedHash) {
		boolean passwordCorrect = false;
		if (getPasswordHash(passwordInput).equals(storedHash)) {
			passwordCorrect = true;
		}
		return passwordCorrect;
	}

	/**
	 * Creates a new invoice using the data from the result set.
	 */
	public static Invoice createInvoice(ResultSet resultSet)  {
		try {

			Subscription.Builder subscriptionBuilder = new Subscription.Builder();
			subscriptionBuilder.setId(resultSet.getLong("subscription_id"));

			Invoice.Builder invoiceBuilder = new  Invoice.Builder();
			invoiceBuilder.setId(resultSet.getLong("id"))
			.setCost(resultSet.getLong("cost"))
			.setSubscription(subscriptionBuilder.build())
			.setCreationDate(LocalDate.parse(resultSet.getString("creation_date")))
			.setStatus(Invoice.Status.valueOf(resultSet.getString("status").toUpperCase()));

			if(resultSet.getString("payment_date")!= null) {
				invoiceBuilder.setPaymentDate(LocalDate.parse(resultSet.getString("payment_date")));
			}
			return invoiceBuilder.build();
		} catch (SQLException e) {
			String message = String.format("Exception during create invoice entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}
}
