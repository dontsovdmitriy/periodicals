package controller.commands.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import controller.commands.Command;
import model.entity.user.User;
import model.service.InvoiceService;
import model.service.impl.InvoiceServiceImpl;

public class InvoiceByTypePaid implements Command {

	private static final Logger logger = Logger.getLogger(InvoiceByTypePaid.class);
	private InvoiceService invoiceService = InvoiceServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		session.setAttribute("invoiceList", invoiceService.findInvoiceByStatusPaid(user));

		logger.info("User " + session.getAttribute("user").toString() + " entered invoice paid view");

		return "/WEB-INF/view/invoiceView.jsp";
		
	}

}
