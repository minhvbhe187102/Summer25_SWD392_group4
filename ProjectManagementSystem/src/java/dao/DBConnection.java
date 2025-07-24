package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lớp này khởi tạo connection tới database
 *
 * @author NamDH
 */
public class DBConnection {

    Connection connection = null;

    public DBConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectManagementSystem", "sa", "123");
            System.out.println("Connect successfully !!");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBConnection(String URL, String username, String pass) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, username, pass);
            System.out.println("Connect successfully !!");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectManagementSystem", "sa", "123");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     * When you are done with using your connection, you need close in order to
     * release any other database resource
     *
     * @param ps it is a object of <code>java.sql.PreparedStatement</code>
     * @throws Exception
     */
    public void closePreparedStatement(PreparedStatement ps) throws Exception {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
    }

    /**
     * When you are done with using your connection, you need close in order to
     * release any other database resource
     *
     * @param con it is a object of <code>java.sql.Connection</code>
     * @throws Exception
     */
    public void closeConnection(Connection con) throws Exception {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    /**
     * When you are done with using your connection, you need close in order to
     * release any other database resource
     *
     * @param rs it is a object of <code>java.sql.ResultSet</code>
     * @throws Exception
     */
    public void closeResultSet(ResultSet rs) throws Exception {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }
    
  
}
