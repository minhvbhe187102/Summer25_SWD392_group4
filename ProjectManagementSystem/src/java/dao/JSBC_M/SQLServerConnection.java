/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.JSBC_M;

/**
 *
 * @author admin
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    public static final String HOSTNAME = "localhost";
    public static final String PORT = "1433";
    public static final String DBNAME = "ProjectManagementSystem";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "123";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        // Create a variable for the connection string
        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                + "databaseName=" + DBNAME + ";encrypt=true;trustServerCertificate=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
