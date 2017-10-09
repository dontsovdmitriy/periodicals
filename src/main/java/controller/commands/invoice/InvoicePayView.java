package controller.commands.view;

import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.entity.user.User;
import model.service.InvoiceService;
import model.service.impl.InvoiceServiceImpl;

public class InvoicePayView implements Command{

	private static final Logger logger = Logger.getLogger(InvoicePayView.class);
	private InvoiceService invoiceService = InvoiceServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		session.setAttribute("invoiceList", invoiceService.findUnpaidUserInvoices(user));

		logger.info("User " + session.getAttribute("user").toString() + " entered invoice pay view");

		return "/WEB-INF/view/payInvoice.jsp";

	}

}
