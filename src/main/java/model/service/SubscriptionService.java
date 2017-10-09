package model.service;

import java.util.List;
import java.util.Optional;

import model.entity.subscription.Subscription;
import model.entity.user.User;

/**
 * The {@code SubscriptionService} interface provides methods for {@code Subscription} object.
 */
public interface SubscriptionService {

	/**
     * Method add subscription
     * @param subscription subscription
     * @return true if the addition is successful
     */
	boolean addSubscription(Subscription subscription);
	
	/**
     * Method find subscription by id
     * @param id
     * @return Optional<Subscription>
     */
	Optional<Subscription> findOneById(long id);
	
	/**
     * Method find all subscription 
     * @return List of subscription 
     */
	List<Subscription> findAll();
	
	/**
     * Method find all active subscription by user
     * @param user user
     * @return List of subscription 
     */
	List<Subscription> findActiveSubscriptions(User user);
	
	/**
     * Method find all active subscription
     * @return List of subscription 
     */
	List<Subscription> findAllActiveSubscriptions();

}
