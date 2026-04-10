package controller;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		String context = req.getContextPath();

		// allow public resources
		if (uri.equals(context + "/") || uri.contains("/login") || uri.contains("/signup") || uri.contains("/views/")
				|| uri.contains("/css/") || uri.contains("/js/") || uri.contains("index.jsp")) {

			chain.doFilter(request, response);
			return;
		}

		HttpSession session = req.getSession(false);

		// ✅ If logged in → allow
		if (session != null && session.getAttribute("user") != null) {
			chain.doFilter(request, response);
		}
		// ❌ If not logged in → redirect properly
		else {
			res.sendRedirect(req.getContextPath() + "/login"); // ✅ FIX
		}
	}
}