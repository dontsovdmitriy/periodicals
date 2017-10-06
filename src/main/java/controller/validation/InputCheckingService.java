package controller.validation;

import model.entity.periodical.*;
import model.entity.subscription.Subscription;
import model.entity.user.User;

public interface InputCheckingService {

	boolean  checkRegistrationForm(User user);
	boolean  checkAddPublisher(Publisher publisher);
	boolean  checkAddPeriodicalCategory(PeriodicalCategory category);
	boolean  checkAddPeriodical(Periodical periodical);
	boolean  checkAddSubscription(Subscription subscription);
	boolean  checkLoginForm(String email, String password);

}
