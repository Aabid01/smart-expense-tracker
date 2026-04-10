package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import dao.ExpenseDAO;
import model.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            User user = (User) req.getSession().getAttribute("user");

            if (user == null) {
                res.sendRedirect("login");
                return;
            }

            List<Expense> list = new ExpenseDAO().getExpenses(user.getId());

            req.setAttribute("expenses", list);
            req.getRequestDispatcher("views/dashboard.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}