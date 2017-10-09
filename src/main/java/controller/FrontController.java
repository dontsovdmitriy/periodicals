
package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.commands.*;
import controller.commands.invoice.*;
import controller.commands.periodical.*;
import controller.commands.subscription.*;
import controller.commands.user.*;

/**
 * The class describes the class for the creation of {@code Command} objects.
 */
public class FrontController extends HttpServlet {

	/**
	 * Hash map of all available commands.
	 */
	private Map<String , Command> commands = new HashMap<>();

	public FrontController() {
		super();
	}

	@Override
	public void init(){

		//Data processing
		commands.put("POST:/addRegistration", new AddRegistrationUser());
		commands.put("POST:/addPublisher", new AddPublisher());
		commands.put("POST:/addCategory", new AddCategory());
		commands.put("POST:/addPeriodical", new AddPeriodical());
		commands.put("POST:/addSubscription", new AddSubscription());
		commands.put("POST:/login", new Login());
		commands.put("GET:/logout", new Logout());
		commands.put("POST:/editPeriodical", new EditPeriodical());
		commands.put("POST:/payInvoice", new InvoicePay());

		//Form processing
		commands.put("GET:/registration", new RegistrationView());
		commands.put("GET:/periodical", new PeriodicalView());
		commands.put("GET:/subscription", new SubscriptionView());
		commands.put("GET:/category", new CategoryView());
		commands.put("GET:/publisher", new PublisherView());
		commands.put("GET:/result", new ResultView());
		commands.put("GET:/login", new LoginView());
		commands.put("GET:/home", new HomeView());
		commands.put("GET:/editPeriodicalView", new EditPeriodicalView());
		commands.put("GET:/activeSubscription",new ActiveSubscriptions());
		commands.put("GET:/activeSubscriptionAll",new ActiveSubscriptionsAll());
		commands.put("GET:/invoicePayView",new InvoicePayView());
		commands.put("GET:/showInvoiceByTypePaid", new InvoiceByTypePaid());
		commands.put("GET:/showInvoiceByTypeUnpaid", new InvoiceByTypeUnpaid());

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request , response);
	}

	/**
	 * The method performs some action depending on the {@code Command} object.
	 *
	 * @param request http servlet request.
	 * @param response http servlet response.
	 * @return the string that describes the path to the corresponding jsp page.
	 */
	void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod().toUpperCase();
		String path = request.getRequestURI();
		path = path.replaceAll(".*/rest", "").replaceAll("/rest", "").replaceAll("\\d+", "");
		String key = method+":"+path;
		Command command = commands.getOrDefault(key, (req , resp)->"/index.jsp" ); 
		String viewPage = command.execute(request, response);

		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
