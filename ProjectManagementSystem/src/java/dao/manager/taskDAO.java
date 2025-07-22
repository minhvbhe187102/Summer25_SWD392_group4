/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.manager;

/**
 *
 * @author zeus
 */



import entity.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class taskDAO {
     private Connection conn;

    public taskDAO(Connection conn) {
        this.conn = conn;
    }

    public void createTask(Task task) throws SQLException {
        String sql = "INSERT INTO Task (task_id, assigned_to, parent_id, name, status, create_date, due_date, complete_date, description, level) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, task.getId());
            stmt.setInt(2, task.getTaskAssined().getId());
            stmt.setInt(3, task.getParentId());
            stmt.setString(4, task.getName());
            stmt.setBoolean(5, task.isStatus());
            stmt.setDate(6, new java.sql.Date(task.getCreateDate().getTime()));
            stmt.setDate(7, new java.sql.Date(task.getDueDate().getTime()));
            stmt.setDate(8, task.getCompleteDate() != null ? new java.sql.Date(task.getCompleteDate().getTime()) : null);
            stmt.setString(9, task.getDescription());
            stmt.setString(10, task.getLevel());
            stmt.executeUpdate();
        }
    }
    public Task getTaskById(int id) throws SQLException {
        String sql = "SELECT * FROM Task WHERE task_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("task_id"));
                int assignedTo = rs.getInt("assigned_to");
                task.setTaskAssined(null); // assuming StaffDAO
                task.setParentId(rs.getInt("parent_id"));
                task.setName(rs.getString("name"));
                task.setStatus(rs.getBoolean("status"));
                task.setCreateDate(rs.getDate("create_date"));
                task.setDueDate(rs.getDate("due_date"));
                task.setCompleteDate(rs.getDate("complete_date"));
                task.setDescription(rs.getString("description"));
                task.setLevel(rs.getString("level"));
                return task;
            }
        }
        return null;
    }

    // 🔹 UPDATE
    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE Task SET assigned_to=?, parent_id=?, name=?, status=?, create_date=?, due_date=?, complete_date=?, description=?, level=? WHERE task_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, task.getTaskAssined().getId());
            stmt.setInt(2, task.getParentId());
            stmt.setString(3, task.getName());
            stmt.setBoolean(4, task.isStatus());
            stmt.setDate(5, new java.sql.Date(task.getCreateDate().getTime()));
            stmt.setDate(6, new java.sql.Date(task.getDueDate().getTime()));
            stmt.setDate(7, task.getCompleteDate() != null ? new java.sql.Date(task.getCompleteDate().getTime()) : null);
            stmt.setString(8, task.getDescription());
            stmt.setString(9, task.getLevel());
            stmt.setInt(10, task.getId());
            stmt.executeUpdate();
        }
    }

    // 🔹 DELETE
    public void deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM Task WHERE task_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // 🔹 READ ALL
    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM Task";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("task_id"));
                int assignedTo = rs.getInt("assigned_to");
                //new StaffDAO(conn).getStaffById(assignedTo)
                task.setTaskAssined();
                task.setParentId(rs.getInt("parent_id"));
                task.setName(rs.getString("name"));
                task.setStatus(rs.getBoolean("status"));
                task.setCreateDate(rs.getDate("create_date"));
                task.setDueDate(rs.getDate("due_date"));
                task.setCompleteDate(rs.getDate("complete_date"));
                task.setDescription(rs.getString("description"));
                task.setLevel(rs.getString("level"));
                tasks.add(task);
            }
        }
        return tasks;
    }
}
