package model.service;

import java.util.List;
import java.util.Optional;

import model.dao.interfaces.*;
import model.entity.invoice.Invoice;
import model.entity.subscription.Subscription;
import model.entity.user.User;

/**
 * The {@code InvoiceService} interface provides methods for {@code Invoice} object.
 */
public interface InvoiceService {
	 
	/**
     * Method add invoice for subscription
     * @param subscription subscription
     * @param connection connection to DB
     * @return true if the addition is successful
     */
	boolean addInvoice(Subscription subscription, DaoConnection connection);
	
	/**
     * Method find invoice by subscription
     * @param subscription subscription
     * @return Optional<Invoice>
     */
	Optional<Invoice> findInvoiceBySubscription(Subscription subscription);
	
	/**
     * Method pays invoice
     * @param invoice invoice
     * @return true if payment is successful
     */
	boolean payInvoice(Invoice invoice);
	
	/**
     * Method return list of user unpaid invoices 
     * @param invoice invoice
     * @return List of invoices
     */
	List<Invoice> findInvoiceByStatusUnpaid(User user);
	
	/**
     * Method return list of user paid invoices 
     * @param invoice invoice
     * @return List of invoices
     */
	List<Invoice> findInvoiceByStatusPaid(User user);	
}
