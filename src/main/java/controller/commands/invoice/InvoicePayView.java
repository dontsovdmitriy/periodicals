package controller.commands.invoice;

import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.entity.user.User;
import model.service.InvoiceService;
import model.service.impl.InvoiceServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing invoices for paying
 */
public class InvoicePayView implements Command{

	private static final String PAGE_TO_GO = "/WEB-INF/view/payInvoice.jsp";

	private static final Logger logger = Logger.getLogger(InvoicePayView.class);
	private InvoiceService invoiceService = InvoiceServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		session.setAttribute("invoiceList", invoiceService.findInvoiceByStatusUnpaid(user));

		logger.info("User " + session.getAttribute("user").toString() + " entered invoice pay view");

		return PAGE_TO_GO;
	}
}
