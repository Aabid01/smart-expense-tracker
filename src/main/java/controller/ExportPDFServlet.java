package controller;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.ExpenseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Expense;
import model.User;

@WebServlet("/exportPDF")
public class ExportPDFServlet extends HttpServlet {

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