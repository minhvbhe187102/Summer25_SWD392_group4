package dao.staff;

import entity.Report;
import entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    // 1. Thêm báo cáo mới
    public boolean insertReport(Report report) {
        String sql = "INSERT INTO Report (user_id, fromDate, toDate, totalTask, completedTask, overdueTask, overdueDate, createdAt, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, report.getUser().getId());
            ps.setDate(2, new java.sql.Date(report.getFromDate().getTime()));
            ps.setDate(3, new java.sql.Date(report.getToDate().getTime()));
            ps.setInt(4, report.getTotalTask());
            ps.setInt(5, report.getCompletedTask());
            ps.setInt(6, report.getOverdueTask());
            //ps.setInt(7, report.getOverdueDate());
            ps.setTimestamp(8, new java.sql.Timestamp(report.getCreatedAt().getTime()));
            ps.setBoolean(9, report.isStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Lấy danh sách báo cáo theo user
    public List<Report> getReportsByUserId(int userId) {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM Report WHERE user_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(userId);

                Report report = new Report(
                    rs.getInt("id"),
                    user,
                    rs.getDate("fromDate"),
                    rs.getDate("toDate"),
                    rs.getInt("totalTask"),
                    rs.getInt("completedTask"),
                    rs.getInt("overdueTask"),
                    //rs.getInt("overdueDate"),
                    rs.getTimestamp("createdAt"),
                    rs.getBoolean("status")
                );

                reports.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    // 3. Duyệt báo cáo (cập nhật status = true)
    public boolean approveReport(int reportId) {
        String sql = "UPDATE Report SET status = 1 WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reportId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Cập nhật các Task liên quan thành "Completed"
    public boolean updateTasksAsCompleted(int userId, Date fromDate, Date toDate) {
        String sql = "UPDATE Task SET status = 'Completed' WHERE user_id = ? AND due_date BETWEEN ? AND ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setDate(2, new java.sql.Date(fromDate.getTime()));
            ps.setDate(3, new java.sql.Date(toDate.getTime()));
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
