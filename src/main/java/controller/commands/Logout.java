package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

public class Logout implements Command {

	private static final Logger logger = Logger.getLogger(Logout.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") != null) {
			logger.info("User " + session.getAttribute("user").toString() +  " logout successful");
			session.invalidate();
		}
		
		return "/WEB-INF/view/home.jsp";
	}

}
