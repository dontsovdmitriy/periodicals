package model.service;

import java.util.List;
import java.util.Optional;

import model.dao.interfaces.*;
import model.entity.invoice.Invoice;
import model.entity.subscription.Subscription;
import model.entity.user.User;

public interface InvoiceService {

	boolean addInvoice(Subscription subscription, DaoConnection connection);
	Optional<Invoice> findInvoiceBySubscription(Subscription subscription);
	boolean payInvoice(Invoice invoice);
	List<Invoice> findInvoiceByStatusUnpaid(User user);
	List<Invoice> findInvoiceByStatusPaid(User user);
	List<Invoice> findUnpaidUserInvoices(User user);
	
}
