-- 1. Tạo database nếu chưa tồn tại
IF DB_ID('ProjectManagementSystem') IS NULL
BEGIN
    CREATE DATABASE ProjectManagementSystem;
END;
GO

USE ProjectManagementSystem;

GO
-- Tạo bảng User
CREATE TABLE [User] (
    user_id INT PRIMARY KEY,
    email NVARCHAR(255),
    full_name NVARCHAR(255),
    password NVARCHAR(255),
    role NVARCHAR(100),
    image NVARCHAR(MAX),
    status BIT
);

GO
-- Tạo bảng Staff
CREATE TABLE Staff (
    staff_id INT PRIMARY KEY,
    user_id INT FOREIGN KEY REFERENCES [User](user_id),
    position NVARCHAR(100),
    level NVARCHAR(100),
    status BIT
);

GO
-- Tạo bảng Task
CREATE TABLE Task (
    task_id INT PRIMARY KEY,
    assigned_to INT FOREIGN KEY REFERENCES [User](user_id),
    parent_id INT,
    name NVARCHAR(255),
    status BIT,
    create_date DATE,
    due_date DATE,
    complete_date DATE,
    description NVARCHAR(MAX),
    level NVARCHAR(100)
);

GO
-- Tạo bảng TaskTopic
CREATE TABLE TaskTopic (
    topic_id INT PRIMARY KEY,
    name NVARCHAR(255),
    description NVARCHAR(MAX),
    status BIT
);

GO
-- Tạo bảng trung gian TaskTopic - Task (nhiều nhiều)
CREATE TABLE TaskTopicMapping (
    task_id INT FOREIGN KEY REFERENCES Task(task_id),
    topic_id INT FOREIGN KEY REFERENCES TaskTopic(topic_id),
    PRIMARY KEY (task_id, topic_id)
);

GO
-- Tạo bảng Project
CREATE TABLE Project (
    project_id INT PRIMARY KEY,
    task_id INT FOREIGN KEY REFERENCES Task(task_id),
    name NVARCHAR(255),
    repository NVARCHAR(255),
    entruster NVARCHAR(255),
    create_date DATE,
    status BIT
);

GO
-- Tạo bảng ProjectRoles
CREATE TABLE ProjectRoles (
    user_id INT FOREIGN KEY REFERENCES [User](user_id),
    project_id INT FOREIGN KEY REFERENCES Project(project_id),
    role_in_project NVARCHAR(100),
    PRIMARY KEY (user_id, project_id)
);

GO
-- Tạo bảng Reports
CREATE TABLE Reports (
    report_id INT PRIMARY KEY,
    user_id INT FOREIGN KEY REFERENCES [User](user_id),
    from_date DATE,
    to_date DATE,
    total_tasks INT,
    completed_tasks INT,
    overdue_tasks DATE,
    created_at DATETIME,
    status BIT
);
