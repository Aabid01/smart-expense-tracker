package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import dao.ExpenseDAO;
import model.*;

@WebServlet("/expense")
public class ExpenseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            User user = (User) req.getSession().getAttribute("user");

            if (user == null) {
                res.sendRedirect("login");
                return;
            }

            String action = req.getParameter("action");
            ExpenseDAO dao = new ExpenseDAO();

            // ✅ ADD EXPENSE (existing functionality)
            if ("add".equals(action)) {

                Expense e = new Expense();
                e.setUserId(user.getId());
                e.setAmount(Double.parseDouble(req.getParameter("amount")));
                e.setCategory(req.getParameter("category"));
                e.setDescription(req.getParameter("description"));
                e.setDate(new java.util.Date());

                dao.addExpense(e);
            }

            // ✅ UPDATE EXPENSE (new functionality)
            else if ("update".equals(action)) {

                int id = Integer.parseInt(req.getParameter("id"));

                Expense e = new Expense();
                e.setId(id);
                e.setUserId(user.getId());
                e.setAmount(Double.parseDouble(req.getParameter("amount")));
                e.setCategory(req.getParameter("category"));
                e.setDescription(req.getParameter("description"));
                e.setDate(new java.util.Date());

                dao.updateExpense(e);
            }

            res.sendRedirect("dashboard");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.sendRedirect("dashboard?error=1");
        }
    }
}