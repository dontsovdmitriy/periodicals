package controller.filters;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import model.entity.user.User;

public class SecurityFilter implements Filter {
	
    private static final Logger logger = Logger.getLogger(SecurityFilter.class);

	private Set<String> generalURI = new HashSet<>();
	private Set<String> userURI = new HashSet<>();
	private Set<String> adminURI = new HashSet<>();

	public void init(FilterConfig fConfig) throws ServletException {

		// general uri
		generalURI.add("/periodicals/rest/addRegistration");
		generalURI.add("/periodicals/rest/login"); 
		generalURI.add("/periodicals/rest/registration");  
		generalURI.add("/periodicals/rest/login"); 
		generalURI.add("/periodicals/rest/home"); 

		// user uri
		userURI.addAll(generalURI);
		userURI.add("/periodicals/rest/addSubscription");
		userURI.add("/periodicals/rest/logout");
		userURI.add("/periodicals/rest/subscription"); 
		userURI.add("/periodicals/rest/showInvoiceByTypeUnpaid"); 
		userURI.add("/periodicals/rest/showInvoiceByTypePaid"); 
		userURI.add("/periodicals/rest/payInvoice"); 
		userURI.add("/periodicals/rest/activeSubscription");
		userURI.add("/periodicals/rest/invoicePayView");
		
		// admin uri
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
		//TODO локализировать
		String errorString = "User is not authorized to access this area!";
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
