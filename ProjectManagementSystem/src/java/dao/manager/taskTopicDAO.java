/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.manager;

/**
 *
 * @author zeus
 */

import dao.JSBC_M.SQLServerConnection;
import entity.Task;
import entity.TaskTopic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class taskTopicDAO {
    
    public ArrayList<Task> getTasksByTopicId(int topicId) {
    ArrayList<Task> list = new ArrayList<>();
    String sql = """
        SELECT t.* 
        FROM TaskTopicMapping m 
        JOIN Task t ON m.task_id = t.task_id 
        WHERE m.topic_id = ?
    """;

    try (Connection con = SQLServerConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, topicId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getInt("task_id"));
            task.setName(rs.getString("name"));
            task.setStatus(rs.getBoolean("status"));
            // add any other Task fields you need (createdAt, dueDate,...)

            list.add(task);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
    
    public ArrayList<TaskTopic> getTopicsByTaskId(int taskId) {
    ArrayList<TaskTopic> list = new ArrayList<>();
    String sql = """
        SELECT tt.* 
        FROM TaskTopicMapping m 
        JOIN TaskTopic tt ON m.topic_id = tt.topic_id 
        WHERE m.task_id = ?
    """;

    try (Connection con = SQLServerConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, taskId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            TaskTopic topic = new TaskTopic();
            topic.setId(rs.getInt("topic_id"));
            topic.setName(rs.getString("name"));
            topic.setDescription(rs.getString("description"));
            topic.setStatus(rs.getBoolean("status"));
            list.add(topic);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
    
    public List<TaskTopic> getAllTopics() {
        List<TaskTopic> list = new ArrayList<>();
        String sql = "SELECT * FROM TaskTopic";

        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TaskTopic t = new TaskTopic(
                    rs.getInt("topic_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBoolean("status")
                    
                );
                t.setTasks(getTasksByTopicId(rs.getInt("topic_id")));
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public TaskTopic getTopicById(int id) {
    String sql = "SELECT * FROM TaskTopic WHERE topic_id = ?";
    try (Connection con = SQLServerConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            TaskTopic topic = new TaskTopic(
                rs.getInt("topic_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBoolean("status")
            );

            topic.setTasks(getTasksByTopicId(id)); // Gán task từ bảng mapping

            return topic;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    public boolean insert(TaskTopic t) {
        String sql = "INSERT INTO TaskTopic(topic_id, name, description, status) VALUES (?, ?, ?, ?)";
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, t.getId());
            ps.setString(2, t.getName());
            ps.setString(3, t.getDescription());
            ps.setBoolean(4, t.isStatus());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(TaskTopic t) {
        String sql = "UPDATE TaskTopic SET name = ?, description = ?, status = ? WHERE topic_id = ?";
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setBoolean(3, t.isStatus());
            ps.setInt(4, t.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM TaskTopic WHERE topic_id = ?";
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
