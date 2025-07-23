/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Report {
    private int id;
    private User user;
    private Date fromDate;
    private Date toDate;
    private int totalTask;
    private int completedTask;
    private int overdueTask;
    private Date overdueDate;
    private Date createdAt;
    private boolean status;

    public Report(int id, User user, Date fromDate, Date toDate, int totalTask, int completedTask, int overdueTask, Date overdueDate, Date createdAt, boolean status) {
        this.id = id;
        this.user = user;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalTask = totalTask;
        this.completedTask = completedTask;
        this.overdueTask = overdueTask;
        this.overdueDate = overdueDate;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Report(int aInt, User user, java.sql.Date date, java.sql.Date date0, int aInt0, int aInt1, int aInt2, Timestamp timestamp, boolean aBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getTotalTask() {
        return totalTask;
    }

    public void setTotalTask(int totalTask) {
        this.totalTask = totalTask;
    }

    public int getCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(int completedTask) {
        this.completedTask = completedTask;
    }

    public int getOverdueTask() {
        return overdueTask;
    }

    public void setOverdueTask(int overdueTask) {
        this.overdueTask = overdueTask;
    }

    public Date getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(Date overdueDate) {
        this.overdueDate = overdueDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
