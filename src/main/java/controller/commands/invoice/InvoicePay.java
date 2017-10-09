package controller.commands.invoice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.entity.invoice.Invoice;
import model.service.*;
import model.service.impl.*;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for paying invoices
 */
public class InvoicePay implements Command {

	private static final Logger logger = Logger.getLogger(InvoicePay.class);

	private static final String PARAM_INVOICE_ID = "invoice";

	private static final String ADD_SUCCESSFUL = "invoicePay.message.paid";
	private static final String ADD_UNSUCCESSFUL = "invoicePay.message.unpaid";

	private static final String PAGE_TO_GO = "/WEB-INF/view/home.jsp";

	private InvoiceService invoiceService = InvoiceServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {  

		HttpSession session = request.getSession();

		Long id = Long.parseLong(request.getParameter(PARAM_INVOICE_ID));

		Invoice invoice = new Invoice.Builder()
				.setId(id)
				.build();

		if (invoiceService.payInvoice(invoice)) {
			request.setAttribute("message", ADD_SUCCESSFUL);
			logger.info("User " + session.getAttribute("user").toString() + " pay invoice with id= " + invoice.getId());
		} else {
			request.setAttribute("message", ADD_UNSUCCESSFUL);
			logger.error("Errors occurred with User " + session.getAttribute("user").toString() + " paying invoice with id= " + invoice.getId());
		}
		return PAGE_TO_GO;
	}
}
