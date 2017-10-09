package model.service.impl;

import java.util.*;

import org.apache.log4j.Logger;

import model.dao.DaoFactory;
import model.dao.interfaces.*;
import model.entity.periodical.Periodical;
import model.entity.subscription.Subscription;
import model.entity.user.User;
import model.service.*;

public class SubscriptionServiceImpl implements SubscriptionService {

	private static final Logger logger = Logger.getLogger(SubscriptionServiceImpl.class);

	DaoFactory daoFactory;

	private static class Holder{
		static final SubscriptionService INSTANCE = new SubscriptionServiceImpl( DaoFactory.getInstance() ); 
	}

	SubscriptionServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static SubscriptionService getInstance(){
		return Holder.INSTANCE;
	}

	public  boolean addSubscription(Subscription subscription) {
		try(DaoConnection connection = daoFactory.getConnection()){

			connection.begin();
			SubscriptionDao subscriptionDao = daoFactory.createSubscriptionDao(connection);

			// invoice auto-generated for subscription
			InvoiceService invoiceService = InvoiceServiceImpl.getInstance();

			long subscriptionId = subscriptionDao.add(subscription);
			if (subscriptionId<0) {
				logger.info("Subscription with periodical = "  + subscription.getPeriodical() + " didn't add to DB");
				connection.rollback();
				return false;
			}
			logger.info("Subscription with periodical = "  + subscription.getPeriodical() + " add to DB");
			subscription.setId(subscriptionId);
			if(!invoiceService.addInvoice(subscription, connection)) {
				logger.info("Invoice with periodical = "  + subscription.getPeriodical() + " didn't add to DB");
				connection.rollback();
			} 
			connection.commit();
			logger.info("Invoice with periodical = "  + subscription.getPeriodical() + " and Subscription with periodical = "  + 
					subscription.getPeriodical() + " add to DB");
			return true; 
		}	
	}

	public  Optional<Subscription> findOneById(long id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			SubscriptionDao subscriptionDao = daoFactory.createSubscriptionDao(connection);
			return subscriptionDao.find(id);
		}
	}

	public List<Subscription> findAll(){
		try(DaoConnection connection = daoFactory.getConnection()){			
			SubscriptionDao subscriptionDao = daoFactory.createSubscriptionDao(connection);
			return subscriptionDao.findAll();
		}
	}

	public List<Subscription> findActiveSubscriptions(User user){
		try(DaoConnection connection = daoFactory.getConnection()){	

			SubscriptionDao subscriptionDao = daoFactory.createSubscriptionDao(connection);
			PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao(connection);
			UserDao userDao = daoFactory.createUserDao(connection);

			List<Subscription> subscriptionsList = subscriptionDao.ActiveSubscriptions(user);
			for (Subscription subscription : subscriptionsList) {
				Optional<Periodical> periodical = periodicalDao.find(subscription.getPeriodical().getId());
				if (periodical.isPresent()) {
					subscription.setPeriodical(periodical.get());
				}	
			}
			for (Subscription subscription : subscriptionsList) {
				Optional<User> userForSubscriptionInput =  userDao.find(subscription.getUser().getId());
				if (userForSubscriptionInput.isPresent()) {
					subscription.setUser(userForSubscriptionInput.get());
				}	
			}
			logger.info("Found active subscription for user with id  = " + user.getId());
			return subscriptionsList;
		}
	}

	public List<Subscription> findAllActiveSubscriptions(){
		try(DaoConnection connection = daoFactory.getConnection()){			
			SubscriptionDao subscriptionDao = daoFactory.createSubscriptionDao(connection);
			PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao(connection);
			UserDao userDao = daoFactory.createUserDao(connection);

			List<Subscription> subscriptionsList = subscriptionDao.ActiveSubscriptionsAll();
			for (Subscription subscription : subscriptionsList) {
				Optional<Periodical> periodical = periodicalDao.find(subscription.getPeriodical().getId());
				if (periodical.isPresent()) {
					subscription.setPeriodical(periodical.get());
				}	
			}
			for (Subscription subscription : subscriptionsList) {
				Optional<User> user =  userDao.find(subscription.getUser().getId());
				if (user.isPresent()) {
					subscription.setUser(user.get());
				}	
			}

			logger.info("Found all active subscription");
			return subscriptionsList;
		}
	}
}
