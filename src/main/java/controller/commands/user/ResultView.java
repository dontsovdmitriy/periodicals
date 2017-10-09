package controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for redirect to result page
 */
public class ResultView implements Command {

	private static final String PAGE_TO_GO = "WEB-INF/view/response.jsp";

	private static final Logger logger = Logger.getLogger(ResultView.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		logger.info("User " + session.getAttribute("user").toString() + " entered result view");

		return PAGE_TO_GO;
	}
}
