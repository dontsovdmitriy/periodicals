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
 * It contains a method for showing periodical for editing
 */
public class EditPeriodicalView implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/periodical/editPeriodical.jsp";

	private static final Logger logger = Logger.getLogger(EditPeriodicalView.class);

	private PublisherService publisherService = PublisherServiceImpl.getInstance();
	private PeriodicalCategoryService periodicalCategoryServicelService = PeriodicalCategoryServiceImpl.getInstance();
	private PeriodicalService periodicalService = PeriodicalServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("periodicalList", periodicalService.findAll());
		session.setAttribute("publisherList", publisherService.findAll());
		session.setAttribute("periodicalCategoryList", periodicalCategoryServicelService.findAll());

		logger.info("User " + session.getAttribute("user").toString() + " entered edit periodical view");

		return PAGE_TO_GO;
	}
}
