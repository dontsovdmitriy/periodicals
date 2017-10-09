package controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for redirect to a login page
 */
public class LoginView implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/user/login.jsp";

    private static final Logger logger = Logger.getLogger(LoginView.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("User  entered login view");
		
		return PAGE_TO_GO;
	}
}
