package controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for redirect to a registration user page
 */
public class RegistrationView implements Command {
	
	private static final String PAGE_TO_GO = "/WEB-INF/view/registration.jsp";
	
    private static final Logger logger = Logger.getLogger(RegistrationView.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		logger.info("User entered registration view");
		
		return PAGE_TO_GO;
	}
}
