package controller;

import java.io.IOException;
import dao.ExpenseDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
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

            if ("add".equals(action)) {

                Expense e = new Expense();
                e.setUserId(user.getId());
                e.setAmount(Double.parseDouble(req.getParameter("amount")));
                e.setCategory(req.getParameter("category"));
                e.setDescription(req.getParameter("description"));
                e.setDate(new java.util.Date());

                dao.addExpense(e);
            }

            else if ("update".equals(action)) {

                Expense e = new Expense();
                e.setId(Integer.parseInt(req.getParameter("id")));
                e.setAmount(Double.parseDouble(req.getParameter("amount")));
                e.setCategory(req.getParameter("category"));
                e.setDescription(req.getParameter("description"));

                dao.updateExpense(e);
            }

            else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.deleteExpense(id);
            }

            res.sendRedirect("dashboard");

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("dashboard?error=1");
        }
    }
}