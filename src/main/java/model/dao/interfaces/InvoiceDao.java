package model.dao.interfaces;

import java.util.*;

import model.entity.invoice.Invoice;
import model.entity.subscription.Subscription;
import model.entity.user.User;

/**
 * The interface describes the behavior of the {@code InvoiceDao} object.
 */
public interface InvoiceDao extends GenericDao<Invoice> {

	/**
	 * Returns the invoice that was generated based on the subscription
	 *
	 * @param registered subscription
	 */
	Optional<Invoice> findBySubscription(Subscription subscription);

	/**
	 * Returns the list of invoices that were generated for a particular user 
	 * and according to the status of the invoice
	 *
	 * @param user
	 * @param invoice status
	 */
	List<Invoice> findAllByUserAndInvoiceStatus(User user, Invoice.Status status);
}
