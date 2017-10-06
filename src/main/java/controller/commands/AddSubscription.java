package controller.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.validation.*;
import model.entity.periodical.Periodical;
import model.entity.subscription.Subscription;
import model.entity.user.User;
import model.service.*;
import model.service.impl.*;

public class AddSubscription implements Command {
	
	private static final Logger logger = Logger.getLogger(AddSubscription.class);

	private static final String PARAM_PERIODICAL = "periodical";
	private static final String PARAM_START_DATE = "startDate";
	private static final String PARAM_NUMBER_MONTH = "numberMonth";
	private static final String PARAM_ADDRESS = "address";

	private static final String REGULAR_EXP_ERROR = "Subscription не прошла проверку регулярки";
	private static final String ADD_SUCCESSFUL = "Subscription добавлени";
	private static final String ADD_UNSUCCESSFUL = "Subscription не добавлени";
	private static final String NULL_ERROR = "Subscription содержит незаполненые значения ";
	private static final String MONTH_ERROR = "Количество месяцев не может быть отрицательным";
	private static final String FIND_ERROR = "Издание отсутствует";

	private SubscriptionService subscriptionService = SubscriptionServiceImpl.getInstance();
	private PeriodicalService periodicalService = PeriodicalServiceImpl.getInstance();
	private InputCheckingService checkingService = new InputCheckingServiceImpl();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String pageToGo="/WEB-INF/view/home.jsp";

		String periodicalStr = request.getParameter(PARAM_PERIODICAL);
		String startDateStr = request.getParameter(PARAM_START_DATE);
		String numberMonthStr = request.getParameter(PARAM_NUMBER_MONTH);
		String address = request.getParameter(PARAM_ADDRESS);
		User user = (User) session.getAttribute("user");

		if (periodicalStr == null || startDateStr == null || numberMonthStr == null || address == null ) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + NULL_ERROR);
			return pageToGo;
		}
		
		int numberMonth = Integer.parseInt(numberMonthStr);
		if (numberMonth<0) {
			request.setAttribute("message", MONTH_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + MONTH_ERROR);
			return pageToGo;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
		LocalDate startDate = LocalDate.parse(startDateStr, formatter);

		Optional<Periodical> periodical = periodicalService.findOneById(Long.parseLong(periodicalStr));

		if (!periodical.isPresent()) {
			request.setAttribute("message", FIND_ERROR);
			return pageToGo;
		}
		
		Subscription subscription = new Subscription.Builder()
				.setAddress(address)
				.setNumberMonth(numberMonth)
				.setPeriodical(periodical.get())
				.setStartDate(startDate)
				.setUser(user)
				.build();


		if(!checkingService.checkAddSubscription(subscription)) {
			request.setAttribute("message", REGULAR_EXP_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + REGULAR_EXP_ERROR);
			return pageToGo;
		}
		
		if (subscriptionService.addSubscription(subscription)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User " + session.getAttribute("user").toString() + " entered subscription " + subscription.getId());
			return pageToGo;
		} 
		
		request.setAttribute("message", ADD_UNSUCCESSFUL);
		logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + ADD_UNSUCCESSFUL);
		return pageToGo;
	}
}

