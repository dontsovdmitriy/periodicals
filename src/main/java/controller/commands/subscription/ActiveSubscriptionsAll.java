package controller.commands.subscription;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.service.SubscriptionService;
import model.service.impl.SubscriptionServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing active subscriptions of all users
 */
public class ActiveSubscriptionsAll implements Command {
	
	private static final String PAGE_TO_GO = "/WEB-INF/view/subscription/subscriptionView.jsp";
	
	private static final Logger logger = Logger.getLogger(ActiveSubscriptionsAll.class);
	private SubscriptionService subscriptionService = SubscriptionServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();		
		session.setAttribute("subscriptionList", subscriptionService.findAllActiveSubscriptions());
				
		logger.info("User " + session.getAttribute("user").toString() + " view all  user active subscription ");

		return PAGE_TO_GO;
	}
}
