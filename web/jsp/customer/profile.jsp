<%@page import="model.Customer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .profile-container {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    .profile-container h2 {
        text-align: center;
        margin-bottom: 20px;
        color: #333;
    }

    .profile-table {
        width: 100%;
        margin-bottom: 20px;
        border-collapse: collapse;
    }

    .profile-table td {
        padding: 8px;
        border-bottom: 1px solid #f0f0f0;
    }

    .profile-table td strong {
        color: #333;
    }

    a {
        display: inline-block;
        margin-top: 20px;
        color: #1e40af;
        text-decoration: none;
    }

    .error-message {
        background-color: #fee2e2;
        color: #b91c1c;
        padding: 12px;
        border: 1px solid #fca5a5;
        border-radius: 8px;
        margin-bottom: 15px;
        text-align: center;
        font-weight: 500;
    }

    .edit-btn {
        display: inline-block;
        background-color: #1e40af;
        color: #ffffff;
        padding: 10px 20px;
        border-radius: 8px;
        text-decoration: none;
        font-weight: 500;
        transition: background-color 0.3s ease, transform 0.2s ease;
        text-align: center;
    }

    .edit-btn:hover {
        background-color: #1d4ed8;
        transform: translateY(-2px);
    }

    .delete-btn {
        display: inline-block;
        background-color: #b91c1c;
        color: #ffffff;
        padding: 10px 20px;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        font-weight: 500;
        transition: background-color 0.3s ease, transform 0.2s ease;
        text-align: center;
    }

    .delete-btn:hover {
        background-color: #dc2626;
        transform: translateY(-2px);
    }

</style>
<title>Highyat - Profile</title>
<%@ include file="../includes/head.jsp" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/customer/login.jsp");
        return;
    }

%>
</head>
<body>
    <%@ include file="../includes/header.jsp" %>
    <main>
        <% if (customer != null) {%>
        <div class="profile-container">
            <h2>Welcome, <%= customer.getName()%></h2>
            <table class="profile-table">
                <tr><td><strong>Email:</strong></td><td><%= customer.getEmail()%></td></tr>

                <% if (customer.getContactNo() != null && !customer.getContactNo().isEmpty()) {%>
                <tr><td><strong>Contact No:</strong></td><td><%= customer.getContactNo()%></td></tr>
                <% } else { %>
                <tr><td><strong>Contact No:</strong></td><td><em>Not provided</em> <a href="editProfile.jsp">(Edit)</a></td></tr>
                <% } %>

                <% if (customer.getAddress() != null && !customer.getAddress().isEmpty()) {%>
                <tr><td><strong>Address:</strong></td><td><%= customer.getAddress()%></td></tr>
                <% } else { %>
                <tr><td><strong>Address:</strong></td><td><em>Not provided</em> <a href="editProfile.jsp">(Edit)</a></td></tr>
                <% }%>

                <tr><td><strong>Account Created On:</strong></td><td><%= customer.getCreatedAt()%></td></tr>
            </table>
            <a href="editProfile.jsp" class="edit-btn">Edit Profile</a>

        </div>
        <form action="<%= request.getContextPath()%>/deleteCustomerServlet" method="post" onsubmit="return confirm('Are you sure you want to delete your account? This action cannot be undone.');" style="text-align: center; margin-top: 10px;">
            <button type="submit" class="delete-btn">Delete Account</button>
        </form>
        <% } else { %>
        <div class="error-message">User profile could not be loaded. Please try again later.</div>
        <% }%>

    </main>
</body>
<%@ include file="../includes/footer.jsp" %>
</html>