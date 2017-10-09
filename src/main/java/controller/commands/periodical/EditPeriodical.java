package controller.commands.periodical;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import controller.validation.*;
import model.entity.periodical.*;
import model.service.*;
import model.service.impl.*;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for editing a periodical
 */
public class EditPeriodical implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/home.jsp";

	private static final Logger logger = Logger.getLogger(EditPeriodical.class);

	private static final String PARAM_ID = "periodical";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_DESCRIPTION = "description";
	private static final String PARAM_COST_PER_MONTH = "costPerMonth";
	private static final String PARAM_PUBLISHER = "publisher";
	private static final String PARAM_CATEGORY = "category";
	private static final String PARAM_STATUS = "status";

	private static final String NULL_ERROR = "editPeriodical.message.nullValue";
	private static final String REGULAR_EXP_ERROR = "editPeriodical.message.regularExp.error";
	private static final String ADD_SUCCESSFUL = "editPeriodical.message.editSuccessful";
	private static final String ADD_UNSUCCESSFUL = "editPeriodical.message.editUnsuccessful";
	private static final String COST_ERROR = "editPeriodical.message.costError";
	private static final String FIND_ERROR = "editPeriodical.message.findError";

	private PeriodicalService periodicalService = PeriodicalServiceImpl.getInstance();
	private PeriodicalCategoryService periodicalCategoryService = PeriodicalCategoryServiceImpl.getInstance();
	private InputCheckingService checkingService = new InputCheckingServiceImpl();
	private PublisherService publisherService = PublisherServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {  

		HttpSession session = request.getSession();

		String id = request.getParameter(PARAM_ID);
		String name = request.getParameter(PARAM_NAME);
		String description = request.getParameter(PARAM_DESCRIPTION);
		String costPerMonthStr = request.getParameter(PARAM_COST_PER_MONTH);
		String publisherStr = request.getParameter(PARAM_PUBLISHER);
		String categoryStr = request.getParameter(PARAM_CATEGORY);
		String status = request.getParameter(PARAM_STATUS);

		if (id == null || name == null || description == null || costPerMonthStr == null || publisherStr == null
				|| categoryStr == null || status ==null ) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + "The periodical contains blank values.");
			return PAGE_TO_GO;
		}

		long costPerMonth = Long.parseLong(costPerMonthStr.replaceAll("[,.]+", ""));
		if (costPerMonth<0) {
			request.setAttribute("message", COST_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + "The subscription price can not be less than 0.");
			return PAGE_TO_GO;
		}

		Optional<Publisher> publisher = publisherService.findPublisher(Long.parseLong(publisherStr));
		Optional<PeriodicalCategory> category = periodicalCategoryService.findOneById(Long.parseLong(categoryStr));

		if (!publisher.isPresent() || !category.isPresent()) {
			request.setAttribute("message", FIND_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + "Publisher or category is not found in the system.");
			return PAGE_TO_GO;
		}

		Periodical periodical = new Periodical.Builder()
				.setId(Long.parseLong(id))
				.setName(name)
				.setDescription(description)
				.setCostPerMonth(costPerMonth)
				.setPublisher(publisher.get())
				.setPeriodicalCategory(category.get())
				.setStatus(Periodical.Status.valueOf(status))
				.build();

		if (!checkingService.checkAddPeriodical(periodical)) {
			request.setAttribute("message", REGULAR_EXP_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + "The periodical was not entered correctly.");
			return PAGE_TO_GO;
		}

		if (periodicalService.editPeriodical(periodical)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User " + session.getAttribute("user").toString() + " edit periodical " + periodical.getName());
			return PAGE_TO_GO;			
		} 

		request.setAttribute("message", ADD_UNSUCCESSFUL);
		logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + "Periodical not edit");
		return PAGE_TO_GO;			
	}
}


