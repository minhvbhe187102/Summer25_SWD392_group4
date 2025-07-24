/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.manager;

import dao.DBConnection;
import entity.Staff;
import entity.Task;
import entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ManagerDashboardDAO extends DBConnection {

    public ArrayList<Task> getAllTask() throws Exception {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "SELECT "
                + "t.task_id, t.assigned_to, t.parent_id, t.name, t.status, "
                + "t.create_date, t.due_date, t.complete_date, t.description, t.level, "
                + "s.staff_id, s.position AS staff_position, s.level AS staff_level, s.status AS staff_status, "
                + "u.user_id, u.email, u.full_name, u.password, u.role, u.image, u.status AS user_status "
                + "FROM Task t "
                + "LEFT JOIN Staff s ON t.assigned_to = s.user_id "
                + "LEFT JOIN [User] u ON s.user_id = u.user_id";
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                // Lấy thông tin User
                User user = null;
                int userId = rs.getInt("user_id");
                if (!rs.wasNull()) {
                    user = new User(
                            userId,
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("image"),
                            rs.getBoolean("user_status"),
                            null // roleInProject
                    );
                }

                // Lấy thông tin Staff
                Staff staff = null;
                int staffId = rs.getInt("staff_id");
                if (!rs.wasNull()) {
                    staff = new Staff(
                            staffId,
                            user,
                            rs.getString("staff_position"),
                            rs.getString("staff_level"),
                            rs.getBoolean("staff_status")
                    );
                }

                // Lấy thông tin Task
                Task task = new Task(
                        rs.getInt("task_id"),
                        staff,
                        rs.getInt("parent_id"),
                        rs.getString("name"),
                        rs.getBoolean("status"),
                        rs.getDate("create_date"),
                        rs.getDate("due_date"),
                        rs.getDate("complete_date"),
                        rs.getString("description"),
                        rs.getString("level")
                );

                taskList.add(task);
            }
            return taskList;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }
    
    public ArrayList<Task> getAllTaskByPriority(String filterPriority) throws Exception {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "SELECT\n"
                + "     t.task_id, t.assigned_to, t.parent_id, t.name, t.status,\n"
                + "     t.create_date, t.due_date, t.complete_date, t.description, t.level,\n"
                + "     s.staff_id, s.position AS staff_position, s.level AS staff_level, s.status AS staff_status,\n"
                + "     u.user_id, u.email, u.full_name, u.password, u.role, u.image, u.status AS user_status\n"
                + "FROM Task t\n"
                + "LEFT JOIN Staff s ON t.assigned_to = s.user_id\n"
                + "LEFT JOIN [User] u ON s.user_id = u.user_id\n"
                + "WHERE t.level = '" + filterPriority + "'";
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                // Lấy thông tin User
                User user = null;
                int userId = rs.getInt("user_id");
                if (!rs.wasNull()) {
                    user = new User(
                            userId,
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("image"),
                            rs.getBoolean("user_status"),
                            null // roleInProject
                    );
                }

                // Lấy thông tin Staff
                Staff staff = null;
                int staffId = rs.getInt("staff_id");
                if (!rs.wasNull()) {
                    staff = new Staff(
                            staffId,
                            user,
                            rs.getString("staff_position"),
                            rs.getString("staff_level"),
                            rs.getBoolean("staff_status")
                    );
                }

                // Lấy thông tin Task
                Task task = new Task(
                        rs.getInt("task_id"),
                        staff,
                        rs.getInt("parent_id"),
                        rs.getString("name"),
                        rs.getBoolean("status"),
                        rs.getDate("create_date"),
                        rs.getDate("due_date"),
                        rs.getDate("complete_date"),
                        rs.getString("description"),
                        rs.getString("level")
                );

                taskList.add(task);
            }
            return taskList;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }
    
    public ArrayList<Task> getAllTaskByStatus(String filterStatus) throws Exception {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "SELECT\n"
                + "     t.task_id, t.assigned_to, t.parent_id, t.name, t.status,\n"
                + "     t.create_date, t.due_date, t.complete_date, t.description, t.level,\n"
                + "     s.staff_id, s.position AS staff_position, s.level AS staff_level, s.status AS staff_status,\n"
                + "     u.user_id, u.email, u.full_name, u.password, u.role, u.image, u.status AS user_status\n"
                + "FROM Task t\n"
                + "LEFT JOIN Staff s ON t.assigned_to = s.user_id\n"
                + "LEFT JOIN [User] u ON s.user_id = u.user_id\n"
                + "WHERE t.status " + filterStatus;
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                // Lấy thông tin User
                User user = null;
                int userId = rs.getInt("user_id");
                if (!rs.wasNull()) {
                    user = new User(
                            userId,
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("image"),
                            rs.getBoolean("user_status"),
                            null // roleInProject
                    );
                }

                // Lấy thông tin Staff
                Staff staff = null;
                int staffId = rs.getInt("staff_id");
                if (!rs.wasNull()) {
                    staff = new Staff(
                            staffId,
                            user,
                            rs.getString("staff_position"),
                            rs.getString("staff_level"),
                            rs.getBoolean("staff_status")
                    );
                }

                // Lấy thông tin Task
                Task task = new Task(
                        rs.getInt("task_id"),
                        staff,
                        rs.getInt("parent_id"),
                        rs.getString("name"),
                        rs.getBoolean("status"),
                        rs.getDate("create_date"),
                        rs.getDate("due_date"),
                        rs.getDate("complete_date"),
                        rs.getString("description"),
                        rs.getString("level")
                );

                taskList.add(task);
            }
            return taskList;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

    public ArrayList<Task> getAllTaskByBothFilter(String filterStatus, String filterPriority) throws Exception {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "SELECT\n"
                + "     t.task_id, t.assigned_to, t.parent_id, t.name, t.status,\n"
                + "     t.create_date, t.due_date, t.complete_date, t.description, t.level,\n"
                + "     s.staff_id, s.position AS staff_position, s.level AS staff_level, s.status AS staff_status,\n"
                + "     u.user_id, u.email, u.full_name, u.password, u.role, u.image, u.status AS user_status\n"
                + "FROM Task t\n"
                + "LEFT JOIN Staff s ON t.assigned_to = s.user_id\n"
                + "LEFT JOIN [User] u ON s.user_id = u.user_id\n"
                + "WHERE t.level = '" + filterPriority + "' AND t.status " + filterStatus;
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                // Lấy thông tin User
                User user = null;
                int userId = rs.getInt("user_id");
                if (!rs.wasNull()) {
                    user = new User(
                            userId,
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("image"),
                            rs.getBoolean("user_status"),
                            null // roleInProject
                    );
                }

                // Lấy thông tin Staff
                Staff staff = null;
                int staffId = rs.getInt("staff_id");
                if (!rs.wasNull()) {
                    staff = new Staff(
                            staffId,
                            user,
                            rs.getString("staff_position"),
                            rs.getString("staff_level"),
                            rs.getBoolean("staff_status")
                    );
                }

                // Lấy thông tin Task
                Task task = new Task(
                        rs.getInt("task_id"),
                        staff,
                        rs.getInt("parent_id"),
                        rs.getString("name"),
                        rs.getBoolean("status"),
                        rs.getDate("create_date"),
                        rs.getDate("due_date"),
                        rs.getDate("complete_date"),
                        rs.getString("description"),
                        rs.getString("level")
                );

                taskList.add(task);
            }
            return taskList;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

    public static void main(String[] args) throws Exception {
        ManagerDashboardDAO managerDashboardDAO = new ManagerDashboardDAO();
        System.out.println(managerDashboardDAO.getAllTaskByPriority("High"));
    }
}
