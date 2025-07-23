package dao.admin;

import entity.Staff;
import entity.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    public List<Staff> getAllStaffWithUserInfo() {
        List<Staff> list = new ArrayList<>();
        String sql = """
            SELECT s.staff_id, s.position, s.level, s.status AS staff_status,
                   u.user_id, u.email, u.fullName, u.password, u.role, u.image, u.status AS user_status, u.roleInProject
            FROM Staff s JOIN User u ON s.user_id = u.user_id
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User(
                    rs.getInt("user_id"),
                    rs.getString("email"),
                    rs.getString("fullName"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("image"),
                    rs.getBoolean("user_status"),
                    rs.getString("roleInProject")
                );

                Staff s = new Staff(
                    rs.getInt("staff_id"),
                    u,
                    rs.getString("position"),
                    rs.getString("level"),
                    rs.getBoolean("staff_status")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
