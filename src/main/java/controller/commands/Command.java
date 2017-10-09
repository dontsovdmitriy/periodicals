package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface describes the behavior of the {@code Command} object.
 */
public interface Command {

	/**
	 * The method performs some action depending on the {@code Command} object.
	 *
	 * @param request http servlet request.
	 * @param response http servlet response.
	 * @return the string that describes the path to the corresponding jsp page.
	 */
	String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
}
