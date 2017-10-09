package controller.commands;

import java.io.IOException;

import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.validation.*;
import model.entity.user.User;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

public class Login implements Command {
	
	private static final Logger logger = Logger.getLogger(Login.class);

	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PASSWORD = "password";

	private static final String REGULAR_EXP_CONFIRM_ERROR = "Email или пароль не прошли проверку регулярки";
	private static final String LOGIN_SUCCESSFUL = "Регистрация добавлена";
	private static final String LOGIN_UNSUCCESSFUL = "Регистрация не добавлена";
	private static final String NULL_ERROR = "Логин содержит незаполненые значения ";

	private UserService userService = UserServiceImpl.getInstance();
	private InputCheckingService checkingService = new InputCheckingServiceImpl();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//TODO на какую страницу ?
		String pageToGo="/WEB-INF/view/home.jsp";

		String email = request.getParameter(PARAM_EMAIL);
		String password = request.getParameter(PARAM_PASSWORD);

		if (email == null || password == null ) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred User with email " + email + ". " + NULL_ERROR);
			return pageToGo;
		}

		if(!checkingService.checkLoginForm(email, password)){
			request.setAttribute("message", REGULAR_EXP_CONFIRM_ERROR);
			logger.error("Errors occurred User with email " + email + ". " + REGULAR_EXP_CONFIRM_ERROR);
			return pageToGo;
		}

		Optional<User> optionalUser = userService.login(email, password);

		if (!optionalUser.isPresent()) {
			request.setAttribute("message", LOGIN_UNSUCCESSFUL);
			logger.error("Errors occurred User with email " + email + ". " + LOGIN_UNSUCCESSFUL);
			return pageToGo;
		} 
		
		User user = optionalUser.get();

		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		logger.info("User with email " + email +  LOGIN_SUCCESSFUL);
		return "/WEB-INF/view/home.jsp";

	}
}



