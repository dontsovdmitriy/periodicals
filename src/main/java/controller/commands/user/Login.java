package controller.commands.user;

import java.util.Optional;

import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import controller.validation.*;
import model.entity.user.User;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for user login
 */
public class Login implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/home.jsp";

	private static final Logger logger = Logger.getLogger(Login.class);

	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PASSWORD = "password";

	private static final String REGULAR_EXP_CONFIRM_ERROR = "login.message.regularExp.error";
	private static final String LOGIN_SUCCESSFUL = "login.message.addSuccessful";
	private static final String LOGIN_UNSUCCESSFUL = "login.message.addUnsuccessful";
	private static final String NULL_ERROR = "login.message.nullValue ";

	private UserService userService;
	private InputCheckingService checkingService;

	public Login() {
		this.userService = UserServiceImpl.getInstance();
		this.checkingService = new InputCheckingServiceImpl();
	}
	public Login(UserService userService, InputCheckingService checkingService) {
		this.userService = userService;
		this.checkingService = checkingService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	{

		String email = request.getParameter(PARAM_EMAIL);
		String password = request.getParameter(PARAM_PASSWORD);

		if (email == null || password == null ) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred User with email " + email + ". " + "The login data contains blank values");
			return PAGE_TO_GO;
		}

		if(!checkingService.checkLoginForm(email, password)){
			request.setAttribute("message", REGULAR_EXP_CONFIRM_ERROR);
			logger.error("Errors occurred User with email " + email + ". " + "The login data was not entered correctly");
			return PAGE_TO_GO;
		}

		Optional<User> optionalUser = userService.login(email, password);

		if (!optionalUser.isPresent()) {
			request.setAttribute("message", LOGIN_UNSUCCESSFUL);
			logger.error("Errors occurred User with email " + email + ". " + "User login unsuccessful");
		} else {		
			User user = optionalUser.get();
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("message", LOGIN_SUCCESSFUL);
			logger.info("User with email " + email +  "User login successful");
		}
		return PAGE_TO_GO;
	}
}



