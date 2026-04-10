package dao;

import java.sql.*;
import java.util.*;
import model.Expense;
import util.DBConnection;

public class ExpenseDAO {

    public void addExpense(Expense e) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO expenses(user_id,amount,category,description,date) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, e.getUserId());
        ps.setDouble(2, e.getAmount());
        ps.setString(3, e.getCategory());
        ps.setString(4, e.getDescription());
        ps.setDate(5, new java.sql.Date(e.getDate().getTime()));

        ps.executeUpdate();
    }

    public List<Expense> getExpenses(int userId) throws Exception {

        List<Expense> list = new ArrayList<>();
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM expenses WHERE user_id=?");
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Expense e = new Expense();
            e.setId(rs.getInt("id"));
            e.setAmount(rs.getDouble("amount"));
            e.setCategory(rs.getString("category"));
            e.setDescription(rs.getString("description"));
            e.setDate(rs.getDate("date"));
            list.add(e);
        }

        return list;
    }

    public void updateExpense(Expense e) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "UPDATE expenses SET amount=?, category=?, description=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setDouble(1, e.getAmount());
        ps.setString(2, e.getCategory());
        ps.setString(3, e.getDescription());
        ps.setInt(4, e.getId());

        ps.executeUpdate();
    }

    public Expense getExpenseById(int id) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM expenses WHERE id=?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Expense e = new Expense();
            e.setId(rs.getInt("id"));
            e.setAmount(rs.getDouble("amount"));
            e.setCategory(rs.getString("category"));
            e.setDescription(rs.getString("description"));
            return e;
        }
        return null;
    }

    public void deleteExpense(int id) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement("DELETE FROM expenses WHERE id=?");
        ps.setInt(1, id);

        ps.executeUpdate();
    }
}