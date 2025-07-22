/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.manager;

import dao.JSBC.SQLServerConnection;
import dao.manager.taskDAO;
import entity.Task;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.sql.Connection;

/**
 *
 * @author zeus
 */
@WebServlet(name="addTask", urlPatterns={"/addTask"})
public class addTask extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addTask</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addTask at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
        // Lấy dữ liệu từ form
        int id = Integer.parseInt(request.getParameter("id"));
        String assignedToStr = request.getParameter("assignedTo"); // có thể null
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        String name = request.getParameter("name");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Date createDate = Date.valueOf(request.getParameter("createDate"));
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));
        String completeDateStr = request.getParameter("completeDate");
        Date completeDate = (completeDateStr == null || completeDateStr.isEmpty()) ? null : Date.valueOf(completeDateStr);
        String description = request.getParameter("description");
        String level = request.getParameter("level");

        // Kết nối
        Connection conn = SQLServerConnection.getConnection();
        //StaffDAO staffDAO = new StaffDAO(conn);
        taskDAO taskDAO = new taskDAO(conn);

        // Tạo Task
        Task task = new Task();
        task.setId(id);

        // Gán người được giao nếu có ID
//        if (assignedToStr != null && !assignedToStr.trim().isEmpty()) {
//            int assignedToId = Integer.parseInt(assignedToStr.trim());
//            Staff staff = staffDAO.getStaffById(assignedToId);
//            if (staff == null) {
//                throw new Exception("Không tìm thấy nhân viên với ID: " + assignedToId);
//            }
//            task.setTaskAssigned(staff);
//        } else {
            task.setTaskAssined(null); // ✅ Cho phép null
        //}

        task.setParentId(parentId);
        task.setName(name);
        task.setStatus(status);
        task.setCreateDate(createDate);
        task.setDueDate(dueDate);
        task.setCompleteDate(completeDate);
        task.setDescription(description);
        task.setLevel(level);

        // Ghi vào DB
        taskDAO.createTask(task);

        response.sendRedirect("listTasks.jsp");

    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "Lỗi khi thêm task: " + e.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
