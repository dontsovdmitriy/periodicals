package controller.validation;

import model.entity.periodical.*;
import model.entity.subscription.Subscription;
import model.entity.user.User;

/**
 * The class contains a methods for checking input values on regular expression
 */
public interface InputCheckingService {

	/**
	 * Method check user registration form on regular expression
	 * @param user for checking
	 * @return true if checking is successful
	 */
	boolean  checkRegistrationForm(User user);

	/**
	 * Method check adding publisher form on regular expression
	 * @param publisher for checking
	 * @return true if checking is successful
	 */
	boolean  checkAddPublisher(Publisher publisher);

	/**
	 * Method check adding periodical category form on regular expression
	 * @param periodical category for checking
	 * @return true if checking is successful
	 */
	boolean  checkAddPeriodicalCategory(PeriodicalCategory category);

	/**
	 * Method check adding periodical form on regular expression
	 * @param periodical for checking
	 * @return true if checking is successful
	 */
	boolean  checkAddPeriodical(Periodical periodical);

	/**
	 * Method check adding subscription form on regular expression
	 * @param subscription for checking
	 * @return true if checking is successful
	 */
	boolean  checkAddSubscription(Subscription subscription);

	/**
	 * Method check user login form on regular expression
	 * @param email 
	 * @param password 
	 * @return true if checking is successful
	 */
	boolean  checkLoginForm(String email, String password);
}
