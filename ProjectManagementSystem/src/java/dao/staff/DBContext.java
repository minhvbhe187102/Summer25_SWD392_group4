/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.staff;

/**
 *
 * @author admin
 */



import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=YourDatabaseName";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
