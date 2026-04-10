package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;  // ✅ ADD THIS
import java.io.*;
import java.util.List;

import dao.ExpenseDAO;
import model.Expense;
import model.User;

@WebServlet("/exportCSV")   // ✅ ADD THIS
public class ExportCSVServlet extends HttpServlet {

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