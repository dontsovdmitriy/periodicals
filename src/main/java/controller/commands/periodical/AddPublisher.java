package controller.commands.periodical;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import controller.validation.*;
import model.entity.periodical.Publisher;
import model.service.PublisherService;
import model.service.impl.PublisherServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for adding a publisher
 */
public class AddPublisher implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/home.jsp";

	private static final Logger logger = Logger.getLogger(AddPublisher.class);

	private static final String PARAM_PUBLISHER = "publisher";

	private static final String REGULAR_EXP_ERROR = "addPublisher.message.regularExp.error";
	private static final String ADD_SUCCESSFUL = "addPublisher.message.addSuccessful";
	private static final String ADD_UNSUCCESSFUL = "addPublisher.message.addUnsuccessful";
	private static final String NULL_ERROR = "addPublisher.message.nullValue";

	private PublisherService publisherService = PublisherServiceImpl.getInstance();
	private InputCheckingService checkingService = new InputCheckingServiceImpl();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String publisherName = request.getParameter(PARAM_PUBLISHER);

		if (publisherName==null) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + "The publisher contains blank values");
			return PAGE_TO_GO;
		}

		Publisher publisher = new Publisher.Builder()
				.setPublisher(publisherName)
				.build();

		if(!checkingService.checkAddPublisher(publisher)) {
			request.setAttribute("message", REGULAR_EXP_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + "The publisher was not entered correctly");
			return PAGE_TO_GO;
		}

		if (publisherService.addPublisher(publisher)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User " + session.getAttribute("user").toString() + " entered publisher " + publisher.getPublisher());
		}

		request.setAttribute("message", ADD_UNSUCCESSFUL);
		logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + "Publisher not added");
		return PAGE_TO_GO;		
	}
}


