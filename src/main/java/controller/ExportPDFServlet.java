package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import dao.ExpenseDAO;
import model.Expense;
import model.User;

@WebServlet("/exportPDF")
public class ExportPDFServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            User user = (User) req.getSession().getAttribute("user");

            if (user == null) {
                res.sendRedirect("login");
                return;
            }

            List<Expense> list = new ExpenseDAO().getExpenses(user.getId());

            res.setContentType("application/pdf");
            res.setHeader("Content-Disposition", "attachment; filename=expenses.pdf");

            Document doc = new Document();
            PdfWriter.getInstance(doc, res.getOutputStream());

            doc.open();
            doc.add(new Paragraph("Expense Report\n\n"));

            PdfPTable table = new PdfPTable(5);

            table.addCell("ID");
            table.addCell("Amount");
            table.addCell("Category");
            table.addCell("Description");
            table.addCell("Date");

            for (Expense e : list) {
                table.addCell(String.valueOf(e.getId()));
                table.addCell(String.valueOf(e.getAmount()));
                table.addCell(e.getCategory());
                table.addCell(e.getDescription());
                table.addCell(String.valueOf(e.getDate()));
            }

            doc.add(table);
            doc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}