package model.dao.interfaces;

import java.util.Optional;

import model.entity.user.User;

/**
 * The interface describes the behavior of the {@code UserDao} object.
 */
public interface UserDao extends GenericDao<User>{

	/**
	 * Returns true if a user with this email is already contained in the database
	 *
	 * @param periodical email
	 */
	boolean emailExistsInDb(String email);

	/**
	 * Returns true if a user with this login is already contained in the database
	 *
	 * @param periodical login
	 */
	boolean loginExistsInDb(String login);

	/**
	 * Retrieves an user by his email.
	 *
	 * @param id must not be null
	 * @return object of the class Optional <User>
	 */
	Optional<User> findByEmail(String email);
}
