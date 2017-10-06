package model.dao.interfaces;

import java.util.List;

import model.entity.invoice.Invoice;
import model.entity.subscription.Subscription;
import model.entity.user.User;

public interface SubscriptionDao extends GenericDao<Subscription>  {

	List<Subscription> findAllByUserAndInvoiceStatus(User user, Invoice.Status status);
	List<Subscription> ActiveSubscriptions(User user);
	List<Subscription> ActiveSubscriptionsAll();

}
