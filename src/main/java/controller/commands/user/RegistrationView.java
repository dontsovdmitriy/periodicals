package controller.commands.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;

public class RegistrationView implements Command {
	
    private static final Logger logger = Logger.getLogger(RegistrationView.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		logger.info("User entered registration view");
		
		return "/WEB-INF/view/registration.jsp";
	}

}
