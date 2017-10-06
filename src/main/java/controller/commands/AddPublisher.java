package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.validation.*;
import model.entity.periodical.Publisher;
import model.service.PublisherService;
import model.service.impl.PublisherServiceImpl;

public class AddPublisher implements Command {
	
	private static final Logger logger = Logger.getLogger(AddPublisher.class);

	private static final String PARAM_PUBLISHER = "publisher";

	private static final String REGULAR_EXP_ERROR = "Publisher не прошла проверку регулярки";
	private static final String ADD_SUCCESSFUL = "Publisher добавлени";
	private static final String ADD_UNSUCCESSFUL = "Publisher не добавлени";
	private static final String NULL_ERROR = "Publisher содержит незаполненые значения";

	private PublisherService publisherService = PublisherServiceImpl.getInstance();
	private InputCheckingService checkingService = new InputCheckingServiceImpl();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String pageToGo="/WEB-INF/view/home.jsp";

		String publisherName = request.getParameter(PARAM_PUBLISHER);

		if (publisherName==null) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + NULL_ERROR);
			return pageToGo;
		}
		
		Publisher publisher = new Publisher.Builder()
				.setPublisher(publisherName)
				.build();
		
		if(!checkingService.checkAddPublisher(publisher)) {
			request.setAttribute("message", REGULAR_EXP_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + REGULAR_EXP_ERROR);
			return pageToGo;
		}
		
		if (publisherService.addPublisher(publisher)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User " + session.getAttribute("user").toString() + " entered publisher " + publisher.getPublisher());
		}
		
		request.setAttribute("message", ADD_UNSUCCESSFUL);
		logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + ADD_UNSUCCESSFUL);
		return pageToGo;
		
	}
}


