package model.service.impl;

import java.time.LocalDate;
import java.util.*;

import org.apache.log4j.Logger;

import model.dao.DaoFactory;
import model.dao.interfaces.*;
import model.entity.invoice.Invoice;
import model.entity.periodical.Periodical;
import model.entity.subscription.Subscription;
import model.entity.user.User;
import model.service.InvoiceService;

public class InvoiceServiceImpl implements InvoiceService {

	private static final Logger logger = Logger.getLogger(InvoiceServiceImpl.class);

	private DaoFactory daoFactory;

	private static class Holder{
		static final InvoiceService INSTANCE = new InvoiceServiceImpl( DaoFactory.getInstance() ); 
	}

	private InvoiceServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static InvoiceService getInstance(){
		return Holder.INSTANCE;
	}

	public  boolean addInvoice(Subscription subscription, DaoConnection connection) {

		InvoiceDao invoiceDao = daoFactory.createInvoiceDao(connection);
	
		long costPerMonth = subscription.getPeriodical().getCostPerMonth();
		long subscriptionCost  = subscription.getNumberMonth() * costPerMonth;

		Invoice invoice = new Invoice.Builder()	
				.setCost(subscriptionCost)
				.setCreationDate(LocalDate.now())
				.setSubscription(subscription)
				.setStatus(Invoice.Status.UNPAID)
				.build();

		if (invoiceDao.add(invoice)>=0) {
			logger.info("Invoice with id= " + invoice.getId() + " add");
			return true; 
		}
		logger.info("Errors occurred writing invoice with id = "  + invoice.getId() + " in DB");
		return false;
	}	
	
	public  Optional<Invoice> findInvoiceBySubscription(Subscription subscription) {
		try(DaoConnection connection = daoFactory.getConnection()){
			InvoiceDao invoiceDao = daoFactory.createInvoiceDao(connection);
			logger.info("Searching invoice with subscription id = " + subscription.getId());
			return invoiceDao.findBySubscription(subscription);
		}
	}

	public boolean payInvoice(Invoice invoice) {
		try(DaoConnection connection = daoFactory.getConnection()){
			InvoiceDao invoiceDao = daoFactory.createInvoiceDao(connection);

			if(!invoiceDao.find(invoice.getId()).isPresent()) {
				logger.info("Errors occurred find invoice with id = "  + invoice.getId());
				return false;
			}
			
			Invoice invoiceDB = invoiceDao.find(invoice.getId()).get();
			invoiceDB.setStatus(Invoice.Status.PAID);
			invoiceDB.setPaymentDate(LocalDate.now());
			if (invoiceDao.update(invoiceDB)>0) {
				logger.info("Invoice with id = " + invoice.getId() + " paid");
				return true; 
			}
			logger.error("Errors occurred paying invoice with id = "  + invoice.getId());
			return false;
		}		
	}

	public List<Invoice> findInvoiceByStatusPaid(User user){
		try(DaoConnection connection = daoFactory.getConnection()){			

			InvoiceDao invoiceDao = daoFactory.createInvoiceDao(connection);
			SubscriptionDao subscriptionDao = daoFactory.createSubscriptionDao(connection);
			PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao(connection);

			List<Invoice> invoiceList = invoiceDao.findAllByUserAndInvoiceStatus(user, Invoice.Status.PAID);
			for (Invoice invoice : invoiceList) {
				Optional<Subscription> subscription = subscriptionDao.find(invoice.getSubscription().getId());
				if (!subscription.isPresent()) {
					logger.info("Subscription  for user id = "  + user.getId() + " didn't find");
				}
				Optional<Periodical> periodical = periodicalDao.find(subscription.get().getPeriodical().getId());
				if (!periodical.isPresent()) {
					logger.info("Paid invoice for user id = "  + user.getId() + " didn't find");
				}
				subscription.get().setPeriodical(periodical.get());
				invoice.setSubscription(subscription.get());
			}
			logger.info("Paid invoice for user id = "  + user.getId() + " found");
			return invoiceList;
		}
	}
	
	public List<Invoice> findInvoiceByStatusUnpaid(User user){
		try(DaoConnection connection = daoFactory.getConnection()){			

			InvoiceDao invoiceDao = daoFactory.createInvoiceDao(connection);
			SubscriptionDao subscriptionDao = daoFactory.createSubscriptionDao(connection);
			PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao(connection);

			List<Invoice> invoiceList = invoiceDao.findAllByUserAndInvoiceStatus(user, Invoice.Status.UNPAID);
			for (Invoice invoice : invoiceList) {
				Optional<Subscription> subscription = subscriptionDao.find(invoice.getSubscription().getId());
				if (!subscription.isPresent()) {
					logger.info("Subscription  for user id = "  + user.getId() + " didn't find");
				}
				Optional<Periodical> periodical = periodicalDao.find(subscription.get().getPeriodical().getId());
				if (!periodical.isPresent()) {
					logger.info("Periodical  for user id = "  + user.getId() + " didn't find");
				}
				subscription.get().setPeriodical(periodical.get());
				invoice.setSubscription(subscription.get());
			}
			logger.info("Unpaid invoice for user id = "  + user.getId() + " found");
			return invoiceList;
		}
	}
}
