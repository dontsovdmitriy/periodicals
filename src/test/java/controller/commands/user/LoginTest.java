package controller.commands.user;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import controller.validation.InputCheckingService;
import model.entity.user.User;
import model.service.UserService;

public class LoginTest {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private InputCheckingService checkingService;
	private UserService userService;
	private Login login;
	
	@Before
	public void init() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		checkingService = mock(InputCheckingService.class);
		userService = mock(UserService.class);
		login = new Login(userService, checkingService);
	}
	
	@Test
	public void tesLoginSuccessful() {
		User user = new User();
		when(request.getParameter("email")).thenReturn("egor123@mail");
		when(request.getParameter("password")).thenReturn("egor123");
		when(request.getSession()).thenReturn(session);
		when(checkingService.checkLoginForm("egor123@mail", "egor123")).thenReturn(true);
		when(userService.login("egor123@mail", "egor123")).thenReturn(Optional.of(user));
		String expected = "/WEB-INF/view/home.jsp";
		String actual = login.execute(request, response);
		assertEquals(expected, actual);
	}
	@Test
	public void testWrongLogin() {
		when(request.getParameter("email")).thenReturn("egor123@mail");
		when(request.getParameter("password")).thenReturn("egor123");
		when(request.getSession()).thenReturn(session);
		when(checkingService.checkLoginForm("egor123@mail", "egor123")).thenReturn(true);
		when(userService.login("egor123@mail", "egor123")).thenReturn(Optional.empty());
		String expected = "/WEB-INF/view/home.jsp";
		String actual = login.execute(request, response);
		assertEquals(expected, actual);

	}
}
