package controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import controller.validation.*;
import model.entity.user.User;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for adding a new user
 */
public class AddRegistrationUser implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/home.jsp";

	private static final Logger logger = Logger.getLogger(AddRegistrationUser.class);

	private static final String PARAM_NAME = "name";
	private static final String PARAM_SURNAME = "surname";
	private static final String PARAM_PHONE = "mobilePhone";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_LOGIN = "login";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_PASSWORD_CONFIRM = "passwordConfirm";

	private static final String REGULAR_EXP_CONFIRM_ERROR = "addResitrationUser.message.regularExp.error";
	private static final String ADD_SUCCESSFUL = "addResitrationUser.message.addSuccessful";
	private static final String ADD_UNSUCCESSFUL = "addResitrationUser.message.addUnsuccessful";
	private static final String NULL_ERROR = "addResitrationUser.message.nullValue";

	private UserService userService;
	private InputCheckingService checkingService;

	public AddRegistrationUser() {
		this.userService = UserServiceImpl.getInstance();;
		this.checkingService = new InputCheckingServiceImpl();
	}
	public AddRegistrationUser(UserService userService, InputCheckingService checkingService) {
		this.userService = userService;
		this.checkingService = checkingService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			return PAGE_TO_GO;
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

		if(!checkingService.checkRegistrationForm(user) || !password.equals(passwordConfirm)){
			request.setAttribute("message", REGULAR_EXP_CONFIRM_ERROR);
			logger.error("Errors occurred with registration User. " + "The registration contains blank values.");
			return PAGE_TO_GO;
		}

		if (userService.addUser(user)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User with id " + user.getId() + "User registration successful");
		} else {
			request.setAttribute("message", ADD_UNSUCCESSFUL);
			logger.error("Errors occurred with registration User. "  + "The registration data was not entered correctly.");
		}
		return PAGE_TO_GO;
	}
}
