package model.service;

import java.util.*;

import model.entity.user.User;

/**
 * The {@code UserService} interface provides methods for {@code User} object.
 */
public interface UserService {
	
	/**
     * Method add user
     * @param subscription subscription
     * @return true if the addition is successful
     */
	boolean addUser(User user);
	
	/**
     * Method for entering the user into the application
     * @param email email
     * @param password password
     * @return Optional<User>
     */
	Optional<User> login(String email, String password);
	
	/**
     * Method find user by id
     * @param id
     * @return Optional<User>
     */
	Optional<User> findOneById(long id);
}


