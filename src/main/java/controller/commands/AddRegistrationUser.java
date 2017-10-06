package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.validation.*;
import model.entity.user.User;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

public class AddRegistrationUser implements Command {
	
	private static final Logger logger = Logger.getLogger(AddRegistrationUser.class);

	private static final String PARAM_NAME = "name";
	private static final String PARAM_SURNAME = "surname";
	private static final String PARAM_PHONE = "mobilePhone";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_LOGIN = "login";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_PASSWORD_CONFIRM = "passwordConfirm";

	private static final String REGULAR_EXP_CONFIRM_ERROR = "Регистрация не прошла проверку регулярки или пароль не соответсвует подтверженному";
	private static final String ADD_SUCCESSFUL = "Регистрация добавлена";
	private static final String ADD_UNSUCCESSFUL = "Регистрация не добавлена";
	private static final String NULL_ERROR = "Регистрация содержит незаполненые значения ";

	private UserService userService = UserServiceImpl.getInstance();
	private InputCheckingService checkingService = new InputCheckingServiceImpl();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageToGo="/WEB-INF/view/home.jsp";

		String name = request.getParameter(PARAM_NAME);
		String surname = request.getParameter(PARAM_SURNAME);
		String mobilePhone = request.getParameter(PARAM_PHONE);
		String email = request.getParameter(PARAM_EMAIL);
		String login = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASSWORD);
		String passwordConfirm = request.getParameter(PARAM_PASSWORD_CONFIRM);

		if (name == null || surname == null || mobilePhone == null || email == null
				|| login == null || password ==null || passwordConfirm ==null) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred with registration User. " + NULL_ERROR);
			return pageToGo;
		}

		User user = new User.Builder()
				.setName(name)
				.setSurname(surname)
				.setMobilePhone(mobilePhone)
				.setEmail(email)
				.setLogin(login)
				.setPassword(password)
				.setType(User.Type.USER)
				.build();

		if(!checkingService.checkRegistrationForm(user) && password.equals(passwordConfirm)){
			request.setAttribute("message", REGULAR_EXP_CONFIRM_ERROR);
			logger.error("Errors occurred with registration User. " + REGULAR_EXP_CONFIRM_ERROR);
			return pageToGo;
		}
		
		if (userService.addUser(user)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User with id " + user.getId() + ADD_SUCCESSFUL);
			return pageToGo;
		} 
		
		request.setAttribute("message", ADD_UNSUCCESSFUL);
		logger.error("Errors occurred with registration User. "  + REGULAR_EXP_CONFIRM_ERROR);
		return pageToGo;
	}
}
