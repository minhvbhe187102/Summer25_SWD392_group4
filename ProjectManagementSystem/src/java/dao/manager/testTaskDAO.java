/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.manager;

import dao.JSBC_M.SQLServerConnection;
import entity.Task;
import java.sql.Connection;
import java.util.Date;
/**
 *
 * @author zeus
 */
public class testTaskDAO {
     public static void main(String[] args) {   
        try (Connection conn = SQLServerConnection.getConnection()) {
            taskDAO taskDAO = new taskDAO(conn);
            Task t = taskDAO.getTaskById(1);
            System.out.println(t.toString()+"aaaaaaaaaaaaaaaaa");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
     }
}
