<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard Quản Lý Công Việc</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            :root {
                --primary: #4361ee;
                --secondary: #3f37c9;
                --success: #4cc9f0;
                --danger: #f72585;
                --warning: #f8961e;
                --light: #f8f9fa;
                --dark: #212529;
                --gray: #6c757d;
                --bg: #f5f7fb;
            }

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            body {
                background-color: var(--bg);
                color: #333;
                line-height: 1.6;
            }

            .dashboard {
                display: grid;
                grid-template-columns: 250px 1fr;
                min-height: 100vh;
            }

            /* Sidebar */
            .sidebar {
                background: linear-gradient(180deg, var(--primary), var(--secondary));
                color: white;
                padding: 20px 0;
                height: 100vh;
                position: fixed;
                width: 250px;
                box-shadow: 3px 0 10px rgba(0,0,0,0.1);
            }

            .logo {
                display: flex;
                align-items: center;
                padding: 0 20px 20px;
                border-bottom: 1px solid rgba(255,255,255,0.1);
                margin-bottom: 20px;
            }

            .logo i {
                font-size: 28px;
                margin-right: 10px;
            }

            .logo h1 {
                font-size: 22px;
                font-weight: 600;
            }

            .menu-item {
                padding: 12px 20px;
                display: flex;
                align-items: center;
                cursor: pointer;
                transition: all 0.3s ease;
            }

            .menu-item:hover, .menu-item.active {
                background: rgba(255,255,255,0.1);
                border-left: 4px solid white;
            }

            .menu-item i {
                margin-right: 10px;
                width: 24px;
                text-align: center;
            }

            /* Main Content */
            .main-content {
                grid-column: 2;
                padding: 20px;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 30px;
            }

            .search-bar {
                background: white;
                border-radius: 50px;
                padding: 8px 15px;
                width: 300px;
                display: flex;
                align-items: center;
                box-shadow: 0 2px 5px rgba(0,0,0,0.05);
            }

            .search-bar input {
                border: none;
                outline: none;
                background: transparent;
                padding: 5px 10px;
                width: 100%;
                font-size: 15px;
            }

            .user-profile {
                display: flex;
                align-items: center;
            }

            .user-profile img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                margin-right: 10px;
                object-fit: cover;
            }

            /* Stats Cards */
            .stats-container {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
                gap: 20px;
                margin-bottom: 30px;
            }

            .stat-card {
                background: white;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 3px 10px rgba(0,0,0,0.05);
                display: flex;
                align-items: center;
                transition: transform 0.3s ease;
            }

            .stat-card:hover {
                transform: translateY(-5px);
            }

            .stat-icon {
                width: 60px;
                height: 60px;
                border-radius: 10px;
                display: flex;
                align-items: center;
                justify-content: center;
                margin-right: 15px;
                font-size: 24px;
            }

            .priority-high .stat-icon {
                background: rgba(247, 37, 133, 0.15);
                color: var(--danger);
            }
            .pending .stat-icon {
                background: rgba(248, 150, 30, 0.15);
                color: var(--warning);
            }
            .expired .stat-icon {
                background: rgba(108, 117, 125, 0.15);
                color: var(--gray);
            }
            .total .stat-icon {
                background: rgba(67, 97, 238, 0.15);
                color: var(--primary);
            }

            .stat-info h3 {
                font-size: 24px;
                margin-bottom: 5px;
            }

            .stat-info p {
                color: var(--gray);
                font-size: 14px;
            }

            /* Filter Section */
            .filters {
                background: white;
                border-radius: 10px;
                padding: 20px;
                margin-bottom: 30px;
                box-shadow: 0 3px 10px rgba(0,0,0,0.05);
            }

            .filters h2 {
                margin-bottom: 15px;
                font-size: 20px;
                display: flex;
                align-items: center;
            }

            .filters h2 i {
                margin-right: 10px;
                color: var(--primary);
            }

            .filter-row {
                display: flex;
                flex-wrap: wrap;
                gap: 15px;
                margin-bottom: 15px;
            }

            .filter-group {
                flex: 1;
                min-width: 200px;
            }

            .filter-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: 500;
                color: var(--dark);
            }

            .filter-group select, .filter-group input {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                background: white;
            }

            .btn-group {
                display: flex;
                justify-content: flex-end;
                gap: 10px;
                margin-top: 10px;
            }

            .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: 500;
                transition: all 0.3s ease;
            }

            .btn-primary {
                background: var(--primary);
                color: white;
            }

            .btn-outline {
                background: transparent;
                border: 1px solid var(--primary);
                color: var(--primary);
            }

            .btn:hover {
                opacity: 0.9;
                transform: translateY(-2px);
            }

            /* Tasks Table */
            .tasks-container {
                background: white;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 3px 10px rgba(0,0,0,0.05);
            }

            .tasks-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .tasks-header h2 {
                display: flex;
                align-items: center;
            }

            .tasks-header h2 i {
                margin-right: 10px;
                color: var(--primary);
            }

            .sort-options select {
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th {
                text-align: left;
                padding: 12px 15px;
                border-bottom: 2px solid #eee;
                color: var(--gray);
                font-weight: 600;
            }

            td {
                padding: 15px;
                border-bottom: 1px solid #eee;
            }

            tr:hover td {
                background: #f9f9ff;
            }

            .priority {
                padding: 5px 10px;
                border-radius: 20px;
                font-size: 12px;
                font-weight: 500;
            }

            .priority-high {
                background: rgba(247, 37, 133, 0.15);
                color: var(--danger);
            }

            .priority-medium {
                background: rgba(248, 150, 30, 0.15);
                color: var(--warning);
            }

            .priority-low {
                background: rgba(76, 201, 240, 0.15);
                color: var(--success);
            }

            .status {
                display: inline-block;
                padding: 5px 12px;
                border-radius: 20px;
                font-size: 12px;
                font-weight: 500;
            }

            .status-pending {
                background: rgba(248, 150, 30, 0.15);
                color: var(--warning);
            }

            .status-completed {
                background: rgba(76, 201, 240, 0.15);
                color: var(--success);
            }

            .status-expired {
                background: rgba(108, 117, 125, 0.15);
                color: var(--gray);
            }

            .action-btn {
                background: transparent;
                border: none;
                color: var(--gray);
                cursor: pointer;
                margin-left: 10px;
                font-size: 16px;
                transition: all 0.3s ease;
            }

            .action-btn:hover {
                color: var(--primary);
            }

            /* Responsive */
            @media (max-width: 992px) {
                .dashboard {
                    grid-template-columns: 1fr;
                }

                .sidebar {
                    position: relative;
                    width: 100%;
                    height: auto;
                }

                .main-content {
                    grid-column: 1;
                }
            }

            @media (max-width: 768px) {
                .header {
                    flex-direction: column;
                    align-items: flex-start;
                }

                .search-bar {
                    width: 100%;
                    margin-bottom: 15px;
                }

                .stat-card {
                    flex-direction: column;
                    text-align: center;
                }

                .stat-icon {
                    margin-right: 0;
                    margin-bottom: 15px;
                }
            }
        </style>
    </head>
    <body>
        <div class="dashboard">
            <!-- Sidebar -->
            <div class="sidebar">
                <div class="logo">
                    <i class="fas fa-tasks"></i>
                    <h1>TaskMaster</h1>
                </div>

                <div class="menu">
                    <div class="menu-item active">
                        <i class="fas fa-home"></i>
                        <span>Dashboard</span>
                    </div>
                    <div class="menu-item">
                        <i class="fas fa-list"></i>
                        <span>Tất cả công việc</span>
                    </div>
                    <div class="menu-item">
                        <i class="fas fa-star"></i>
                        <span>Quan trọng</span>
                    </div>
                    <div class="menu-item">
                        <i class="fas fa-calendar-times"></i>
                        <span>Sắp hết hạn</span>
                    </div>
                    <div class="menu-item">
                        <i class="fas fa-check-circle"></i>
                        <span>Đã hoàn thành</span>
                    </div>
                    <div class="menu-item">
                        <i class="fas fa-cog"></i>
                        <span>Cài đặt</span>
                    </div>
                    <div class="menu-item">
                        <i class="fas fa-sign-out-alt"></i>
                        <span>Đăng xuất</span>
                    </div>
                </div>
            </div>

            <!-- Main Content -->
            <div class="main-content">
                <div class="header">
                    <div class="search-bar">
                        <i class="fas fa-search"></i>
                        <input type="text" placeholder="Tìm kiếm công việc...">
                    </div>
                    <div class="user-profile">
                        <img src="https://randomuser.me/api/portraits/men/41.jpg" alt="User">
                        <div>
                            <div class="user-name">Nguyễn Văn A</div>
                            <div class="user-role">Quản lý dự án</div>
                        </div>
                    </div>
                </div>

                <c:set var="highPriority" value="0"></c:set>
                <c:forEach var="task" items="${taskList}">
                    <c:if test="${task.level eq 'High'}">
                        <c:set var="highPriority" value="${highPriority + 1}" />
                    </c:if>
                </c:forEach>

                <!-- Stats Cards -->
                <div class="stats-container">
                    <div class="stat-card priority-high">
                        <div class="stat-icon">
                            <i class="fas fa-exclamation-circle"></i>
                        </div>
                        <div class="stat-info">
                            <h3>${highPriority}</h3>
                            <p>Ưu tiên cao</p>
                        </div>
                    </div>

                    <c:set var="unfinished" value="0"></c:set>
                    <c:forEach var="task" items="${taskList}">
                        <c:if test="${task.status == null}">
                            <c:set var="unfinished" value="${unfinished + 1}" />
                        </c:if>
                    </c:forEach>

                    <div class="stat-card pending">
                        <div class="stat-icon">
                            <i class="fas fa-clock"></i>
                        </div>
                        <div class="stat-info">
                            <h3>${unfinished}</h3>
                            <p>Chưa hoàn thành</p>
                        </div>
                    </div>


                    <c:set var="due" value="0"></c:set>
                    <c:forEach var="task" items="${taskList}">
                        <c:if test="${!task.status}">
                            <c:set var="due" value="${due + 1}" />
                        </c:if>
                    </c:forEach>

                    <div class="stat-card expired">
                        <div class="stat-icon">
                            <i class="fas fa-calendar-exclamation"></i>
                        </div>
                        <div class="stat-info">
                            <h3>${due}</h3>
                            <p>Đã hết hạn</p>
                        </div>
                    </div>

                    <c:set var="total" value="0"></c:set>
                    <c:forEach var="task" items="${taskList}">
                        <c:set var="total" value="${total + 1}" />
                    </c:forEach>

                    <div class="stat-card total">
                        <div class="stat-icon">
                            <i class="fas fa-tasks"></i>
                        </div>
                        <div class="stat-info">
                            <h3>${total}</h3>
                            <p>Tổng công việc</p>
                        </div>
                    </div>
                </div>

                <!-- Filters -->
                <div class="filters">
                    <h2><i class="fas fa-filter"></i> Bộ lọc công việc</h2>
                    <form action="${contextPath}/ManagerDashboardController" method="get">
                        <input type="hidden" name="service" value="filter"/>
                        <div class="filter-row">
                            <div class="filter-group">
                                <label>Trạng thái</label>
                                <select name="status_filter" id="status-filter">
                                    <option value="all">Tất cả</option>
                                    <option value="is null">Chưa hoàn thành</option>
                                    <option value="= 1">Đã hoàn thành</option>
                                    <option value="= 0">Hết hạn</option>
                                </select>
                            </div>

                            <div class="filter-group">
                                <label>Mức độ ưu tiên</label>
                                <select name="priority" id="priority-filter">
                                    <option value="all">Tất cả</option>
                                    <option value="High">Cao</option>
                                    <option value="Medium">Trung bình</option>
                                    <option value="Low">Thấp</option>
                                </select>
                            </div>
                        </div>

                        <div class="btn-group">
                            <button class="btn btn-primary">Áp dụng bộ lọc</button>
                        </div>
                    </form>
                </div>

                <!-- Tasks Table -->
                <div class="tasks-container">
                    <div class="tasks-header">
                        <h2><i class="fas fa-list"></i> Danh sách công việc</h2>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <th width="40%">Công việc</th>
                                <th width="15%">Ưu tiên</th>
                                <th width="15%">Hạn chót</th>
                                <th width="15%">Trạng thái</th>
                                <th width="15%">Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="task" items="${taskList}">
                                <tr>
                                    <td>
                                        <div class="task-title">${task.name}</div>
                                        <div class="task-desc">${task.description}</div>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${task.level eq 'High'}">
                                                <span class="priority priority-high">${task.level}</span>
                                            </c:when>
                                            <c:when test="${task.level eq 'Medium'}">
                                                <span class="priority priority-medium">${task.level}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="priority priority-low">${task.level}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${task.dueDate}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${task.status == null}">
                                                <span class="status status-pending">Chưa hoàn thành</span>
                                            </c:when>
                                            <c:when test="${task.status}">
                                                <span class="status status-completed">Đã hoàn thành</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="status status-expired">Hết hạn</span>
                                            </c:otherwise>
                                        </c:choose>

                                    </td>
                                    <td>
                                        <button class="action-btn"><i class="fas fa-edit"></i></button>
                                        <button class="action-btn"><i class="fas fa-check"></i></button>
                                        <button class="action-btn"><i class="fas fa-trash"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script>

        </script>
    </body>
</html>