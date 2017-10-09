package model.dao.interfaces;

/**
 * The interface describes the behavior of the {@code DaoConnection} object.
 */
public interface DaoConnection extends AutoCloseable {
	/**
	 * The method begins transaction.
	 */
	void begin();
	/**
	 * The method commits transaction.
	 */
	void commit();
	/**
	 * The method rollbacks transaction.
	 */
	void rollback();
	/**
	 * The method closes transaction.
	 */
	void close();
}
