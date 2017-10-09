package model.dao.interfaces;

import java.util.List;

import model.entity.invoice.Invoice;
import model.entity.subscription.Subscription;
import model.entity.user.User;

/**
 * The interface describes the behavior of the {@code SubscriptionDao} object.
 */
public interface SubscriptionDao extends GenericDao<Subscription>  {

	/**
	 * Returns the list of subscriptions that  have been issued by a particular user 
	 * and according to the status of the invoice
	 * 
	 * @param user
	 * @param invoice status
	 */
	List<Subscription> findAllByUserAndInvoiceStatus(User user, Invoice.Status status);

	/**
	 * Returns a list of active subscriptions that have been issued by a particular user
	 * 
	 * @param user
	 */
	List<Subscription> ActiveSubscriptions(User user);

	/**
	 * Returns a list of active subscriptions of all users
	 */
	List<Subscription> ActiveSubscriptionsAll();
}
