package controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();

		if (uri.contains("login") || uri.contains("signup") || uri.contains("css") || uri.contains("js")) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = req.getSession(false);

		if (session != null && session.getAttribute("user") != null) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect("login");
		}
	}
}