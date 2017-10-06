package controller.validation;

import java.time.format.DateTimeFormatter;

import controller.validation.InputCheckingService;
import model.entity.periodical.*;
import model.entity.subscription.Subscription;
import model.entity.user.User;

public class InputCheckingServiceImpl implements InputCheckingService{

	public static final String NAME_REGEX = "[A-Za-zЄ-ЯҐа-їґ]{2,50}";
	public static final String PERIODICAL_NAME_REGEX = "[A-Za-zЄ-ЯҐа-їґ0-9'\\.\\-\\s\\,]{2,50}";
	public static final String MONEY_REGEX = "[0-9]+([,.][0-9]{1,2})?";
	public static final String DESCRIPTION_REGEX = "[A-Za-zЄ-ЯҐа-їґ0-9'\\.\\-\\s\\,]{2,500}";
	public static final String PUBLISHER_REGEX = "[A-Za-zЄ-ЯҐа-їґ]{2,50}";
	//TODO проверить все ли регулярки подходят для проверки соответстующих типов
	//TODO найти лучше регулярку для номера телефона 
	public static final String PHONE_NUMBER_REGEX = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
	public static final String EMAIL_REGEX = "([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
	public static final String LOGIN_REGEX = "[A-Za-z0-9_-]{3,16}";
	public static final String PASSWORD_REGEX = "[A-Za-zЄ-ЯҐа-їґ0-9_-]{6,18}";
	public static final String DATE_REGEX = "\\d{2}.\\d{2}\\.\\d{4}";
	public static final String NUMBER_MONTH_REGEX = "\\d{1,2}";
	public static final String ADDRESS_REGEX = "[A-Za-zЄ-ЯҐа-їґ0-9'\\.\\-\\s\\,]{10,200}";

	@Override
	public  boolean checkRegistrationForm(User user) {

		return 	user.getName().matches(NAME_REGEX) &&
				user.getSurname().matches(NAME_REGEX) &&
				user.getMobilePhone().matches(PHONE_NUMBER_REGEX) &&
				user.getEmail().matches(EMAIL_REGEX) &&
				user.getLogin().matches(LOGIN_REGEX) &&
				user.getPassword().matches(PASSWORD_REGEX);
			
	}

	@Override
	public boolean checkAddPublisher(Publisher publisher) {
		return publisher.getPublisher().matches(PUBLISHER_REGEX);
	}

	@Override
	public boolean checkAddPeriodicalCategory(PeriodicalCategory category) {
		return category.getCategoryName().matches(NAME_REGEX);
	}

	@Override
	public boolean checkAddPeriodical(Periodical periodical) {
		return 	periodical.getName().matches(PERIODICAL_NAME_REGEX) &&
				periodical.getDescription().matches(DESCRIPTION_REGEX) &&
			    String.valueOf(periodical.getCostPerMonth()).matches(MONEY_REGEX);	
	}

	@Override
	public boolean checkAddSubscription(Subscription subscription) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String startDate = subscription.getStartDate().format(formatter);
		System.out.println(startDate);
		
		return 	startDate.matches(DATE_REGEX) &&
				String.valueOf(subscription.getNumberMonth()).matches(NUMBER_MONTH_REGEX) &&
				subscription.getAddress().matches(ADDRESS_REGEX);
	}

	@Override
	public boolean checkLoginForm(String email, String password) {
		return email != null &&
				password != null &&
				email.matches(EMAIL_REGEX) &&
				password.matches(PASSWORD_REGEX);
	}
}
