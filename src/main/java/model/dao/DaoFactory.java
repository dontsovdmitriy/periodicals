package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import model.dao.interfaces.*;

/**
 * {@code DaoFactory} is the abstract class that describes the behavior of the {@code DaoFactory} object.
 */
public abstract class DaoFactory {

	private static final Logger logger = Logger.getLogger(DaoFactory.class);

	/**
	 * The method retrieves {@code DaoConnection} object from the connection pool.
	 *
	 * @return {@code DaoConnection} object.
	 */
	public abstract DaoConnection getConnection();

	/**
	 * The method creates {@code UserDao} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code UserDao} object.
	 */
	public abstract UserDao createUserDao(DaoConnection connection);

	/**
	 * The method creates {@code PublisherDao} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code PublisherDao} object.
	 */
	public abstract PublisherDao createPublisherDao(DaoConnection connection);

	/**
	 * The method creates {@code PeriodicalCategoryDao} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code PeriodicalCategoryDao} object.
	 */
	public abstract PeriodicalCategoryDao createPeriodicalCategoryDao(DaoConnection connection);

	/**
	 * The method creates {@code PeriodicalDao} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code PeriodicalDao} object.
	 */
	public abstract PeriodicalDao createPeriodicalDao(DaoConnection connection);

	/**
	 * The method creates {@code SubscriptionDao} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code SubscriptionDao} object.
	 */
	public abstract SubscriptionDao createSubscriptionDao(DaoConnection connection);

	/**
	 * The method creates {@code InvoiceDao} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code InvoiceDao} object.
	 */
	public abstract InvoiceDao createInvoiceDao(DaoConnection connection);

	/**
	 * File name with properties for DB
	 */
	public static final String DB_FILE = "/db.properties";

	/**
	 * Factory class name. It should be change depending on which factory you use.
	 */
	private static final String DB_FACTORY_CLASS = "factory.class";

	/**
	 * {@code DaoFactory} instance.
	 */
	private static DaoFactory instance;

	/**
	 * The methods provides creating or getting already created {@code DaoFactory} object.
	 *
	 * @return {@code DaoFactory} object.
	 */
	public static DaoFactory getInstance() {
		if (instance == null) {
			try {
				InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
				Properties dbProperties = new Properties();
				dbProperties.load(inputStream);

				instance = (DaoFactory) Class.forName(dbProperties.getProperty(DB_FACTORY_CLASS)).newInstance();

			} catch (IOException | IllegalAccessException |
					ClassNotFoundException | InstantiationException e) {
				logger.error("Error during the DaoConnection getting: ", e);
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
}
