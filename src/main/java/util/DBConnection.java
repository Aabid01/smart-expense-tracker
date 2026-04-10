package util;

import java.sql.*;

public class DBConnection {

	private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL")
			: "jdbc:mysql://localhost:3306/expense_tracker";

	private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";

	private static final String PASS = System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : "qwerty";

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASS);
	}
}