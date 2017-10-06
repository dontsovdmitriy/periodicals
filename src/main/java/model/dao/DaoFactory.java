package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import model.dao.interfaces.*;

public abstract class DaoFactory {
	
    private static final Logger logger = Logger.getLogger(DaoFactory.class);

    public abstract DaoConnection getConnection();
    /*   public abstract UserDao createUserDao();
    public abstract PublisherDao createPublisherDao();
    public abstract PeriodicalCategoryDao createPeriodicalCategoryDao();
    public abstract PeriodicalDao createPeriodicalDao();
    public abstract SubscriptionDao createSubscriptionDao();
    public abstract InvoiceDao createInvoiceDao();*/

    public abstract UserDao createUserDao(DaoConnection connection);
    public abstract PublisherDao createPublisherDao(DaoConnection connection);
    public abstract PeriodicalCategoryDao createPeriodicalCategoryDao(DaoConnection connection);
    public abstract PeriodicalDao createPeriodicalDao(DaoConnection connection);
    public abstract SubscriptionDao createSubscriptionDao(DaoConnection connection);
    public abstract InvoiceDao createInvoiceDao(DaoConnection connection);

	public static final String DB_FILE = "/db.properties";
	private static final String DB_FACTORY_CLASS = "factory.class";
	private static DaoFactory instance;

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
