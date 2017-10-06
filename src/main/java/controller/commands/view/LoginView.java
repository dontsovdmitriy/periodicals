package controller.commands.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;

public class LoginView implements Command {

    private static final Logger logger = Logger.getLogger(LoginView.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("User  entered login view");
		
		return "/WEB-INF/view/login.jsp";

	}

}
