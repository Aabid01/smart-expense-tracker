package controller;

import java.io.IOException;

import dao.ExpenseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Expense;

@WebServlet("/editExpense")
public class EditExpenseServlet extends HttpServlet {

    @Override
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