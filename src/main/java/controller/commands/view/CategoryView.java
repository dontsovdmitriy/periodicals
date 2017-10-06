package controller.commands.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;

public class CategoryView implements Command {
	
    private static final Logger logger = Logger.getLogger(CategoryView.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
        logger.info("User " + session.getAttribute("user").toString() + " entered category view");
		return "/WEB-INF/view/addCategory.jsp";
	}

}
