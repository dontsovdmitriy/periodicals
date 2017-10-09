package controller.commands.subscription;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.entity.user.User;
import model.service.SubscriptionService;
import model.service.impl.SubscriptionServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing active subscriptions by user
 */
public class ActiveSubscriptions implements Command {
	
	private static final String PAGE_TO_GO = "/WEB-INF/view/subscriptionView.jsp";
	
	private static final Logger logger = Logger.getLogger(ActiveSubscriptions.class);
	private SubscriptionService subscriptionService = SubscriptionServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		session.setAttribute("subscriptionList", subscriptionService.findActiveSubscriptions(user));
				
		logger.info("User " + session.getAttribute("user").toString() + " view active subscription");

		return PAGE_TO_GO;
	}
}
