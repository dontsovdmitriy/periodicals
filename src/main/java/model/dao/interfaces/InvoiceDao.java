package model.dao.interfaces;

import java.util.*;

import model.entity.invoice.Invoice;
import model.entity.subscription.Subscription;
import model.entity.user.User;

public interface InvoiceDao extends GenericDao<Invoice> {
    Optional<Invoice> findBySubscription(Subscription subscription);
	List<Invoice> findAllByUserAndInvoiceStatus(User user, Invoice.Status status);
}
