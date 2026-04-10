package controller;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("views/signup.jsp").forward(req, res);
    }

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        User u = new User();
        u.setName(req.getParameter("name"));
        u.setEmail(req.getParameter("email"));
        u.setPassword(req.getParameter("password"));

        if (new UserDAO().register(u)) {
            res.sendRedirect("login?success=1");
        } else {
            res.sendRedirect("signup?error=1");
        }
    }
}