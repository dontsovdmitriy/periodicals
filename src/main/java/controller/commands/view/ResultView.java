package controller.commands.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;

public class ResultView implements Command {
	
    private static final Logger logger = Logger.getLogger(ResultView.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		logger.info("User " + session.getAttribute("user").toString() + " entered result view");
		
		return "/WEB-INF/view/response.jsp";
	}

}
