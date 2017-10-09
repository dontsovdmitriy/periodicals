package controller.commands.user;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import controller.validation.InputCheckingService;
import model.entity.user.User;
import model.service.UserService;

public class AddRegistrationUserTest {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private InputCheckingService checkingService;
	private UserService userService;
	private AddRegistrationUser registrationUser;

	@Before
	public void init() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		checkingService = mock(InputCheckingService.class);
		userService = mock(UserService.class);
		registrationUser = new AddRegistrationUser(userService, checkingService);
	}

	@Test
	public void registration_successful() throws Exception {
		User user = new User();
		when(checkingService.checkRegistrationForm(user)).thenReturn(true);
		when(userService.addUser(user)).thenReturn(true);
		String page =  registrationUser.execute(request, response);
		String expected = "/WEB-INF/view/home.jsp";
		assertEquals(expected, page);
	}
}
