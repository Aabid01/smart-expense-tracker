package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String rawUrl = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");

        // ✅ Local fallback (for testing)
        if (rawUrl == null) {
            rawUrl = "jdbc:mysql://localhost:3306/expense_tracker";
            user = "root";
            pass = "your_password";
        }
        // ✅ Railway URL fix
        else {
            rawUrl = rawUrl.replace("mysql://", "jdbc:mysql://")
                           + "?useSSL=false&allowPublicKeyRetrieval=true";
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(rawUrl, user, pass);
    }
}