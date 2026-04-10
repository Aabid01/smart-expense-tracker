package controller;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req, res);
    }

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new UserDAO().login(email, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            res.sendRedirect("dashboard");
        } else {
            res.sendRedirect("login?error=1");
        }
    }
}