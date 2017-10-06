package controller.commands;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.validation.*;
import model.entity.periodical.*;
import model.service.*;
import model.service.impl.*;

public class EditPeriodical implements Command {
	
	private static final Logger logger = Logger.getLogger(EditPeriodical.class);

	private static final String PARAM_ID = "periodical";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_DESCRIPTION = "description";
	private static final String PARAM_COST_PER_MONTH = "costPerMonth";
	private static final String PARAM_PUBLISHER = "publisher";
	private static final String PARAM_CATEGORY = "category";
	private static final String PARAM_STATUS = "status";

	private static final String NULL_ERROR = "Periodical содержит незаполненые значения";
	private static final String REGULAR_EXP_ERROR = "Periodical не прошла проверку регулярки";
	private static final String ADD_SUCCESSFUL = "Periodical отредактирован";
	private static final String ADD_UNSUCCESSFUL = "Periodical не отредактирован";
	private static final String COST_ERROR = "Стоимость подписки не может быть меньше 0";
	private static final String FIND_ERROR = "Издатель или категория отсутствует";

	private PeriodicalService periodicalService = PeriodicalServiceImpl.getInstance();
	private PeriodicalCategoryService periodicalCategoryService = PeriodicalCategoryServiceImpl.getInstance();
	private InputCheckingService checkingService = new InputCheckingServiceImpl();
	private PublisherService publisherService = PublisherServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {  

		//TODO вынести в константы
		String pageToGo="/WEB-INF/view/home.jsp";
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
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + NULL_ERROR);
			return pageToGo;
		}
		
		long costPerMonth = Long.parseLong(costPerMonthStr.replaceAll("[,.]+", ""));
		if (costPerMonth<0) {
			request.setAttribute("message", COST_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + COST_ERROR);
			return pageToGo;
		}
		
		Optional<Publisher> publisher = publisherService.findPublisher(Long.parseLong(publisherStr));
		Optional<PeriodicalCategory> category = periodicalCategoryService.findOneById(Long.parseLong(categoryStr));

		if (!publisher.isPresent() || !category.isPresent()) {
			request.setAttribute("message", FIND_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + FIND_ERROR);
			return pageToGo;
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
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + REGULAR_EXP_ERROR);
			return pageToGo;
		}
		
		if (periodicalService.editPeriodical(periodical)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User " + session.getAttribute("user").toString() + " edit periodical " + periodical.getName());
			return pageToGo;			
		} 
		
		request.setAttribute("message", ADD_UNSUCCESSFUL);
		logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ", " + ADD_UNSUCCESSFUL);
		return pageToGo;			

	}
}


