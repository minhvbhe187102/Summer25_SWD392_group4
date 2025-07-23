/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Task {
    private int id;
    private Staff taskAssined;
    private int parentId;
    private String name;
    private boolean status;
    private Date createDate;
    private Date dueDate;
    private Date completeDate;
    private String description;
    private String level;

    public Task(int id, Staff taskAssined, int parentId, String name, boolean status, Date createDate, Date dueDate, Date completeDate, String description, String level) {
        this.id = id;
        this.taskAssined = taskAssined;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.createDate = createDate;
        this.dueDate = dueDate;
        this.completeDate = completeDate;
        this.description = description;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Staff getTaskAssined() {
        return taskAssined;
    }

    public void setTaskAssined(Staff taskAssined) {
        this.taskAssined = taskAssined;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", taskAssined=" + taskAssined + ", parentId=" + parentId + ", name=" + name + ", status=" + status + ", createDate=" + createDate + ", dueDate=" + dueDate + ", completeDate=" + completeDate + ", description=" + description + ", level=" + level + '}' + "\n";
    }
    
    
}
