package controller;

import java.io.IOException;
import java.util.List;

import dao.ExpenseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Expense;
import model.User;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            User user = (User) req.getSession().getAttribute("user");

            if (user == null) {
                res.sendRedirect(req.getContextPath() + "/login");  // ✅
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