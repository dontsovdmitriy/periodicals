package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.validation.*;
import model.entity.periodical.PeriodicalCategory;
import model.service.PeriodicalCategoryService;
import model.service.impl.PeriodicalCategoryServiceImpl;

public class AddCategory implements Command {

	private static final Logger logger = Logger.getLogger(AddCategory.class);

	private static final String PARAM_CATEGORY = "category";

	//TODO локализировать константы
	private static final String REGULAR_EXP_ERROR = "Category не прошла проверку регулярки";
	private static final String ADD_SUCCESSFUL = "Category добавлени";
	private static final String ADD_UNSUCCESSFUL = "Category не добавлени";
	private static final String NULL_ERROR = "Category содержит незаполненые значения";

	private PeriodicalCategoryService categoryService = PeriodicalCategoryServiceImpl.getInstance();
	private InputCheckingService checkingService = new InputCheckingServiceImpl();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 

		String 	pageToGo = "/WEB-INF/view/home.jsp";
		HttpSession session = request.getSession();

		String categoryName = request.getParameter(PARAM_CATEGORY);
		if(categoryName == null) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + NULL_ERROR);
			return pageToGo;
		}
		
		PeriodicalCategory category = new PeriodicalCategory.Builder()
				.setCategoryName(categoryName)
				.build();
		
		if(!checkingService.checkAddPeriodicalCategory(category)) {
			request.setAttribute("message", REGULAR_EXP_ERROR);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + REGULAR_EXP_ERROR);
			return pageToGo; 
		}
		
		if (categoryService.addCategory(category)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User " + session.getAttribute("user").toString() + " entered category " + category.getCategoryName());
			return pageToGo; 
		}
		
		request.setAttribute("message", ADD_UNSUCCESSFUL);
		logger.error("Errors occurred with User " + session.getAttribute("user").toString() + ". " + ADD_UNSUCCESSFUL);
		return pageToGo; 

	}
}


