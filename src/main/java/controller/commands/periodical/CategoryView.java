package controller.commands.periodical;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing category
 */
public class CategoryView implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/periodical/addCategory.jsp";

	private static final Logger logger = Logger.getLogger(CategoryView.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		logger.info("User " + session.getAttribute("user").toString() + " entered category view");
		return PAGE_TO_GO;
	}
}
