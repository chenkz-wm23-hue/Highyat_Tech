<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String role = (String) session.getAttribute("role");
    String userName = (String) session.getAttribute("userName");

    if (role == null) {
        response.sendRedirect("userLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Highyat Admin</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <style>
        .dashboard-container {
            max-width: 1000px;
            margin: 40px auto;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 20px;
            padding: 20px;
        }

        .dashboard-card {
            background-color: white;
            border-radius: 12px;
            padding: 25px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.07);
            text-align: center;
            transition: transform 0.2s ease;
        }

        .dashboard-card:hover {
            transform: translateY(-4px);
        }

        .dashboard-card h3 {
            margin-bottom: 15px;
            font-size: 20px;
        }

        .dashboard-card a {
            text-decoration: none;
            color: white;
            background-color: #111827;
            padding: 10px 16px;
            border-radius: 8px;
            font-weight: bold;
        }

        .dashboard-card a:hover {
            background-color: #1f2937;
        }

        .greeting {
            text-align: center;
            margin-top: 30px;
        }
    </style>
</head>
<body class="with-sidebar">

<%@ include file="../includes/header-staff-admin.jsp" %>

<main>
    <div class="greeting">
        <h2>Welcome, <%= userName %>!</h2>
        <p>You are logged in as <strong><%= role.toUpperCase() %></strong></p>
    </div>

    <div class="dashboard-container">

        <% if ("admin".equals(role)) { %>
            <div class="dashboard-card">
                <h3>Manage Customers</h3>
                <a href="<%= request.getContextPath() %>/manageCustomers.jsp">Go</a>
            </div>
            <div class="dashboard-card">
                <h3>Manage Staff</h3>
                <a href="<%= request.getContextPath() %>/manageStaff.jsp">Go</a>
            </div>
        <% } %>

        <% if ("admin".equals(role) || "staff".equals(role)) { %>
            <div class="dashboard-card">
                <h3>Manage Products</h3>
                <a href="<%= request.getContextPath() %>/manageProducts.jsp">Go</a>
            </div>

            <div class="dashboard-card">
                <h3>View Customers</h3>
                <a href="<%= request.getContextPath() %>/viewCustomers.jsp">Go</a>
            </div>
        <% } %>

    </div>
</main>

<%@ include file="../includes/footer.jsp" %>

</body>
</html>
