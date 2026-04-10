package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import dao.UserDAO;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req, res);
    }

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