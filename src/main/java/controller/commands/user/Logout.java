package controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for user logout
 */
public class Logout implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/home.jsp";

	private static final Logger logger = Logger.getLogger(Logout.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") != null) {
			logger.info("User " + session.getAttribute("user").toString() +  " logout successful");
			session.invalidate();
		} 
		
		return PAGE_TO_GO;
	}
}
