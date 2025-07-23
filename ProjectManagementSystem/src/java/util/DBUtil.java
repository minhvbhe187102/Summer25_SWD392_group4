package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ProjectManagementSystem";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "123"; // thay bằng mật khẩu thực tế

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
