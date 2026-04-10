package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import dao.ExpenseDAO;
import model.Expense;

@WebServlet("/editExpense")
public class EditExpenseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            // Get expense id from URL
            int id = Integer.parseInt(req.getParameter("id"));

            ExpenseDAO dao = new ExpenseDAO();
            Expense expense = dao.getExpenseById(id);

            // Send expense to JSP
            req.setAttribute("expense", expense);

            // Forward to edit page
            req.getRequestDispatcher("views/editExpense.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("dashboard");
        }
    }
}