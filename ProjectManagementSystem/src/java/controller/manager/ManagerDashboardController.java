/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.manager.ManagerDashboardDAO;
import entity.Task;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ManagerDashboardController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            ManagerDashboardDAO managerDashboardDAO = new ManagerDashboardDAO();
            
            if (service.equals("list")) {
                ArrayList<Task> taskList = managerDashboardDAO.getAllTask();
                request.setAttribute("taskList", taskList);
                request.getRequestDispatcher("jsp.manager/manager_dashboard.jsp").forward(request, response);
            }

            if (service.equals("filter")) {
                String priority = request.getParameter("priority");
                String status = request.getParameter("status_filter");
                ArrayList<Task> taskList = new ArrayList<>();
                if (priority.equals("all") && status.equals("all")) {
                    taskList = managerDashboardDAO.getAllTask();
                } else if (!priority.equals("all") && status.equals("all")) {
                    taskList = managerDashboardDAO.getAllTaskByPriority(priority);
                } else if (priority.equals("all") && !status.equals("all")) {
                    taskList = managerDashboardDAO.getAllTaskByStatus(status);
                } else {
                    taskList = managerDashboardDAO.getAllTaskByBothFilter(status, priority);
                }
                request.setAttribute("taskList", taskList);
                request.getRequestDispatcher("jsp.manager/manager_dashboard.jsp").forward(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManagerDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManagerDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
