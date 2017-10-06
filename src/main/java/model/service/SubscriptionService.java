package model.service;

import java.util.List;
import java.util.Optional;

import model.entity.subscription.Subscription;
import model.entity.user.User;

public interface SubscriptionService {

	boolean addSubscription(Subscription subscription);
	Optional<Subscription> findOneById(long id);
	List<Subscription> findAll();
	List<Subscription> findActiveSubscriptions(User user);
	List<Subscription> findAllActiveSubscriptions();

}
