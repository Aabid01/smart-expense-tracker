package controller;

import java.io.IOException;

import dao.ExpenseDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            // Get expense id
            int id = Integer.parseInt(req.getParameter("id"));

            // Delete from DB
            ExpenseDAO dao = new ExpenseDAO();
            dao.deleteExpense(id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back to dashboard
        res.sendRedirect("dashboard");
    }
}