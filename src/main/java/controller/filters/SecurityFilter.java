package controller.filters;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import model.entity.user.User;

/**
 * {@code SecurityFilter} is the class that describes security filter.
 * It forbids the access to resources for the users that don't have necessary rights.
 */
public class SecurityFilter implements Filter {

	private static final Logger logger = Logger.getLogger(SecurityFilter.class);

	/**
	 * Set of allowed uri's for all users.
	 */
	private Set<String> generalURI = new HashSet<>();

	/**
	 * A set of allowed uri's for the user who logged
	 */
	private Set<String> userURI = new HashSet<>();

	/**
	 * Set of allowed uri's for the admin.
	 */
	private Set<String> adminURI = new HashSet<>();

	public void init(FilterConfig fConfig) throws ServletException {

		generalURI.add("/periodicals/rest/addRegistration");
		generalURI.add("/periodicals/rest/login"); 
		generalURI.add("/periodicals/rest/registration");  
		generalURI.add("/periodicals/rest/login"); 
		generalURI.add("/periodicals/rest/home"); 
		generalURI.add("/periodicals/rest/logout");

		userURI.addAll(generalURI);
		userURI.add("/periodicals/rest/addSubscription");
		userURI.add("/periodicals/rest/subscription"); 
		userURI.add("/periodicals/rest/showInvoiceByTypeUnpaid"); 
		userURI.add("/periodicals/rest/showInvoiceByTypePaid"); 
		userURI.add("/periodicals/rest/payInvoice"); 
		userURI.add("/periodicals/rest/activeSubscription");
		userURI.add("/periodicals/rest/invoicePayView");

		adminURI.addAll(userURI);
		adminURI.add("/periodicals/rest/addPublisher");
		adminURI.add("/periodicals/rest/addCategory");
		adminURI.add("/periodicals/rest/addPeriodical");
		adminURI.add("/periodicals/rest/periodical");
		adminURI.add("/periodicals/rest/category");
		adminURI.add("/periodicals/rest/publisher");
		adminURI.add("/periodicals/rest/editPeriodicalView");
		adminURI.add("/periodicals/rest/editPeriodical");
		adminURI.add("/periodicals/rest/activeSubscriptionAll");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String errorString = "security.message.error";
		HttpServletRequest requestS = (HttpServletRequest) request;
		HttpServletResponse responseS = (HttpServletResponse) response;
		User user = (User) requestS.getSession().getAttribute("user");
		String uri = requestS.getRequestURI();
		logger.info("encoding: URL " + uri);

		boolean isAllowedGuestAccess =
				(user == null) &&
				generalURI.contains(uri);
		boolean isAllowedUserAccess =
				(user != null) &&
				"USER".equals(user.getType().name()) &&
				userURI.contains(uri);
		boolean isAllowedAdminAccess =
				(user != null) &&
				"ADMIN".equals(user.getType().name()) &&
				adminURI.contains(uri);

		if (isAllowedGuestAccess || isAllowedUserAccess || isAllowedAdminAccess) {
			chain.doFilter(requestS, responseS);
		} else {
			request.setAttribute("message", errorString);
			request.getRequestDispatcher("/rest/home").forward(request,response);
		}
	}

	public void destroy() {
	}
}
