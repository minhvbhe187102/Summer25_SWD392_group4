package controller.admin;

import dao.admin.StaffDAO;
import entity.Staff;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

public class StaffListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        StaffDAO dao = new StaffDAO();
        List<Staff> staffList = dao.getAllStaffWithUserInfo();

        request.setAttribute("staffList", staffList);
        request.getRequestDispatcher("/jsp.admin/staff_list.jsp").forward(request, response);
    }
}
