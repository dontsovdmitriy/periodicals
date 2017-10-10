package controller.filters;

import java.io.IOException;
import javax.servlet.*;

import org.apache.log4j.Logger;

/**
 * Allows entering on the frontend and saving cyrillic symbols in the system.
 */
public class EncodingFilter implements Filter {

	private static final Logger logger = Logger.getLogger(EncodingFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		logger.info("encoding: charset " + request.getCharacterEncoding());
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
