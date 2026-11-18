<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession headerSession = request.getSession(false);
    String headerRole = headerSession != null ? (String) headerSession.getAttribute("role") : null;
    boolean isLoggedIn = headerRole != null && (headerRole.equals("admin") || headerRole.equals("staff"));
%>

<% if (isLoggedIn) { %>
<header style="background-color: #111827; padding: 20px; color: white;">
    <div style="display: flex; justify-content: space-between; align-items: center;">
        <div>
            <h1 style="margin: 0;">Highyat</h1>
            <span style="font-size: 18px;">Admin</span>
        </div>
    </div>

    <nav style="margin-top: 10px;">
        <a href="<%= request.getContextPath() %>/jsp/admin/dashboard.jsp" style="margin-right: 15px; color: white;">Dashboard</a>

        <% if ("admin".equals(headerRole)) { %>
            <a href="<%= request.getContextPath() %>/jsp/admin/manageUsers.jsp" style="margin-right: 15px; color: white;">Manage Customers</a>
            <a href="<%= request.getContextPath() %>/jsp/admin/manageStaff.jsp" style="margin-right: 15px; color: white;">Manage Staff</a>
        <% } %>

        <% if ("admin".equals(headerRole) || "staff".equals(headerRole)) { %>
            <a href="<%= request.getContextPath() %>/jsp/admin/manageProducts.jsp" style="margin-right: 15px; color: white;">Manage Products</a>
            <a href="<%= request.getContextPath() %>/jsp/admin/viewCustomers.jsp" style="margin-right: 15px; color: white;">View Customers</a>
        <% } %>

        <a href="<%= request.getContextPath() %>/adminLogout" style="color: #ef4444; font-weight: bold;">Logout</a>
    </nav>
</header>
<% } %>
