/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.admin;

import entity.Staff;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StaffDAOTest {

    @Test
    public void testGetAllStaffWithUserInfo() {
        StaffDAO staffDAO = new StaffDAO();

        List<Staff> staffList = staffDAO.getAllStaffWithUserInfo();

        // Kiểm tra danh sách không null
        assertNotNull(staffList, "Danh sách nhân viên không được null");

        // Kiểm tra có ít nhất 1 staff (tuỳ theo dữ liệu thật trong DB)
        assertTrue(staffList.size() > 0, "Phải có ít nhất một nhân viên trong DB");

        // In ra thông tin để kiểm tra bằng mắt
        for (Staff staff : staffList) {
            System.out.println("Staff ID: " + staff.getStaffId());
            System.out.println("Position: " + staff.getPosition());
            System.out.println("Level: " + staff.getLevel());
            System.out.println("Status: " + staff.isStatus());

            if (staff.getUser() != null) {
                System.out.println("User Full Name: " + staff.getUser().getFullName());
                System.out.println("Email: " + staff.getUser().getEmail());
            }

            System.out.println("--------");
        }
    }
}

