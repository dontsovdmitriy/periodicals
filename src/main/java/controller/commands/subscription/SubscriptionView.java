package controller.commands.subscription;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.service.PeriodicalService;
import model.service.impl.PeriodicalServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing subscriptions
 */
public class SubscriptionView implements Command {
	
	private static final String PAGE_TO_GO = "/WEB-INF/view/subscription/addSubscription.jsp";

    private static final Logger logger = Logger.getLogger(SubscriptionView.class);
	private PeriodicalService periodicalService = PeriodicalServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("periodicalsList", periodicalService.findAll());

		logger.info("User " + session.getAttribute("user").toString() + " entered periodical view");
		
		return PAGE_TO_GO;
	}
}
