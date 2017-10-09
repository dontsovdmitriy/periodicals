package controller.commands.periodical;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import controller.validation.*;
import model.entity.periodical.PeriodicalCategory;
import model.service.PeriodicalCategoryService;
import model.service.impl.PeriodicalCategoryServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for adding a subscription
 */
public class AddCategory implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/home.jsp";

	private static final Logger logger = Logger.getLogger(AddCategory.class);

	private static final String PARAM_CATEGORY = "category";

	private static final String REGULAR_EXP_ERROR = "addCategory.message.regularExp.error";
	private static final String ADD_SUCCESSFUL = "addCategory.message.addSuccessful";
	private static final String ADD_UNSUCCESSFUL = "addCategory.message.addUnSuccessful";
	private static final String NULL_ERROR = "addCategory.message.nullValue";

	private PeriodicalCategoryService categoryService = PeriodicalCategoryServiceImpl.getInstance();
	private InputCheckingService checkingService = new InputCheckingServiceImpl();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 

		HttpSession session = request.getSession();

		String categoryName = request.getParameter(PARAM_CATEGORY);
		if(categoryName == null) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + " The category contains blank values");
			return PAGE_TO_GO;
		}

		PeriodicalCategory category = new PeriodicalCategory.Builder()
				.setCategoryName(categoryName)
				.build();

		if(!checkingService.checkAddPeriodicalCategory(category)) {
			request.setAttribute("message", REGULAR_EXP_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + " The category entered incorrectly");
			return PAGE_TO_GO; 
		}

		if (categoryService.addCategory(category)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User " + session.getAttribute("user").toString() + " entered category " + category.getCategoryName());
		} else {
			request.setAttribute("message", ADD_UNSUCCESSFUL);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + " Category not added");
		}
		return PAGE_TO_GO; 
	}
}


