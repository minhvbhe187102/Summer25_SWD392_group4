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

GO
-- 1. Chèn dữ liệu vào bảng User (5 người dùng)
INSERT INTO [User] (user_id, email, full_name, password, role, image, status) VALUES
(1, 'manager@company.com', N'Nguyễn Văn Quản lý', 'hashed_pass1', 'manager', 'manager.jpg', 1),
(2, 'dev1@company.com', N'Trần Thị Developer', 'hashed_pass2', 'staff', 'dev1.jpg', 1),
(3, 'dev2@company.com', N'Lê Văn Lập Trình', 'hashed_pass3', 'staff', 'dev2.jpg', 1),
(4, 'intern@company.com', N'Phạm Thực Tập', 'hashed_pass4', 'intern', 'intern.jpg', 1),
(5, 'client@company.com', N'Đỗ Khách Hàng', 'hashed_pass5', 'client', NULL, 1);

GO
-- 2. Chèn dữ liệu vào bảng Staff (3 nhân viên)
INSERT INTO Staff (staff_id, user_id, position, level, status) VALUES
(101, 2, 'Senior Developer', 'Senior', 1),
(102, 3, 'Junior Developer', 'Junior', 1),
(103, 4, 'Intern', 'Fresher', 1);

GO
-- 3. Chèn dữ liệu vào bảng Task (10 công việc)
INSERT INTO Task (task_id, assigned_to, parent_id, name, status, create_date, due_date, complete_date, description, level) VALUES
-- Task cha
(1001, 2, NULL, N'Phát triển module quản lý', null, '2024-01-15', '2024-02-28', NULL, N'Xây dựng module quản lý người dùng', 'High'),
(1002, 3, NULL, N'Thiết kế giao diện', null, '2024-01-10', '2024-03-15', '2024-03-10', N'Thiết kế UI/UX cho ứng dụng', 'Medium'),

-- Task con của 1001
(1003, 2, 1001, N'API đăng nhập', null, '2024-01-20', '2024-02-10', '2024-02-05', N'Phát triển API xác thực', 'High'),
(1004, 3, 1001, N'Frontend form', null, '2024-01-22', '2024-02-15', NULL, N'Tạo form nhập liệu', 'Medium'),

-- Task con của 1002
(1005, 3, 1002, N'Thiết kế homepage', 1, '2024-01-12', '2024-02-20', '2024-02-18', N'Thiết kế trang chủ responsive', 'Low'),
(1006, 4, 1002, N'Animation loading', 0, '2024-01-18', '2024-03-01', NULL, N'Tạo hiệu ứng tải trang', 'Low'),

-- Task độc lập
(1007, 4, NULL, N'Kiểm thử đơn vị', null, '2024-02-01', '2024-03-30', NULL, N'Viết unit test cho module', 'Medium'),
(1008, 2, NULL, N'Triển khai production', null, '2024-01-25', '2024-02-05', '2024-02-04', N'Deploy lên server production', 'High'),
(1009, 3, NULL, N'Fix lỗi responsive', null, '2024-02-10', '2024-02-25', NULL, N'Sửa lỗi giao diện mobile', 'High'),
(1010, 4, 1007, N'Kiểm thử API', null, '2024-02-05', '2024-03-10', NULL, N'Kiểm thử API bằng Postman', 'Medium');

GO
-- 4. Chèn dữ liệu vào bảng TaskTopic (3 chủ đề)
INSERT INTO TaskTopic (topic_id, name, description, status) VALUES
(2001, N'Phát triển backend', N'Các công việc liên quan đến server-side', 1),
(2002, N'Thiết kế frontend', N'Các công việc giao diện người dùng', 1),
(2003, N'Kiểm thử', N'Các công việc kiểm thử phần mềm', 1);

GO
-- 5. Chèn dữ liệu vào bảng TaskTopicMapping (quan hệ nhiều-nhiều)
INSERT INTO TaskTopicMapping (task_id, topic_id) VALUES
(1001, 2001),
(1003, 2001),
(1004, 2002),
(1002, 2002),
(1005, 2002),
(1007, 2003),
(1010, 2003),
(1009, 2002);

GO
-- 6. Chèn dữ liệu vào bảng Project (2 dự án)
INSERT INTO Project (project_id, task_id, name, repository, entruster, create_date, status) VALUES
(3001, 1001, N'Website quản lý', 'https://github.com/company/projectA', N'Công ty ABC', '2024-01-01', 1),
(3002, 1002, N'Ứng dụng di động', 'https://github.com/company/projectB', N'Tập đoàn XYZ', '2024-02-01', 1);

GO
-- 7. Chèn dữ liệu vào bảng ProjectRoles (vai trò trong dự án)
INSERT INTO ProjectRoles (user_id, project_id, role_in_project) VALUES
(1, 3001, 'Project Manager'),
(2, 3001, 'Tech Lead'),
(3, 3001, 'Developer'),
(4, 3001, 'Tester'),
(1, 3002, 'Project Manager'),
(3, 3002, 'Main Developer'),
(4, 3002, 'Support');

GO
-- 8. Chèn dữ liệu vào bảng Reports (2 báo cáo)
INSERT INTO Reports (report_id, user_id, from_date, to_date, total_tasks, completed_tasks, overdue_tasks, created_at, status) VALUES
(4001, 2, '2024-02-01', '2024-02-29', 5, 3, '2024-02-28', GETDATE(), 1),
(4002, 3, '2024-02-01', '2024-02-29', 8, 5, '2024-03-01', GETDATE(), 1);