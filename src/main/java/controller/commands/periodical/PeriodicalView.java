package controller.commands.periodical;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.service.*;
import model.service.impl.*;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing periodical
 */
public class PeriodicalView implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/periodical/addPeriodical.jsp";

	private static final Logger logger = Logger.getLogger(PeriodicalView.class);

	private PublisherService publisherService = PublisherServiceImpl.getInstance();
	private PeriodicalCategoryService periodicalCategoryServicelService = PeriodicalCategoryServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("publisherList", publisherService.findAll());
		session.setAttribute("periodicalCategoryList", periodicalCategoryServicelService.findAll());

		logger.info("User " + session.getAttribute("user").toString() + " entered periodical view");

		return PAGE_TO_GO;
	}
}
