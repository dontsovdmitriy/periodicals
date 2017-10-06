package controller.commands.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.service.SubscriptionService;
import model.service.impl.SubscriptionServiceImpl;

public class ActiveSubscriptionsAll implements Command {
	
	private static final Logger logger = Logger.getLogger(ActiveSubscriptionsAll.class);
	private SubscriptionService subscriptionService = SubscriptionServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();		
		session.setAttribute("subscriptionList", subscriptionService.findAllActiveSubscriptions());
				
		logger.info("User " + session.getAttribute("user").toString() + " view all  user active subscription ");

		return "/WEB-INF/view/subscriptionView.jsp";
	}

}
