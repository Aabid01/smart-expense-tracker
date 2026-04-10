package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.ExpenseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;  // ✅ ADD THIS
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Expense;
import model.User;

@WebServlet("/exportCSV")   // ✅ ADD THIS
public class ExportCSVServlet extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            User user = (User) req.getSession().getAttribute("user");

            if (user == null) {
                res.sendRedirect("login");
                return;
            }

            List<Expense> list = new ExpenseDAO().getExpenses(user.getId());

            res.setContentType("text/csv");
            res.setHeader("Content-Disposition", "attachment; filename=expenses.csv");

            PrintWriter writer = res.getWriter();

            writer.println("ID,Amount,Category,Description,Date");

            for (Expense e : list) {
                writer.println(e.getId() + "," + e.getAmount() + "," +
                        e.getCategory() + "," + e.getDescription() + "," + e.getDate());
            }

            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}