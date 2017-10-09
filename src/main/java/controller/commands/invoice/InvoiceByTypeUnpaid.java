package controller.commands.invoice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.entity.user.User;
import model.service.InvoiceService;
import model.service.impl.InvoiceServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing unpaid invoices
 */
public class InvoiceByTypeUnpaid implements Command {

	private static final String PAGE_TO_GO = "/WEB-INF/view/invoiceView.jsp";

	private static final Logger logger = Logger.getLogger(InvoiceByTypeUnpaid.class);
	private InvoiceService invoiceService = InvoiceServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		session.setAttribute("invoiceList", invoiceService.findInvoiceByStatusUnpaid(user));

		logger.info("User " + session.getAttribute("user").toString() + " entered invoice unpaid view");

		return PAGE_TO_GO;		
	}
}
