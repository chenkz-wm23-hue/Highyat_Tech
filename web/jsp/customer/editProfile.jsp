<%@page import="model.Customer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<style>
    .edit-form-container {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    .edit-form-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    .edit-form-container form {
        display: flex;
        flex-direction: column;
    }

    .edit-form-container label {
        margin-bottom: 5px;
        font-weight: bold;
    }

    .edit-form-container input[type="text"],
    .edit-form-container input[type="email"],
    .edit-form-container textarea {
        margin-bottom: 15px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .edit-form-container input[type="submit"] {
        background-color: #1e40af;
        color: white;
        padding: 10px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
</style>
<title>Highyat - Edit Profile</title>
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
        <div class="edit-form-container">
            <h2>Edit Profile</h2>
            <form action="<%= request.getContextPath()%>/updateProfileServlet" method="post">
                <label>Name:</label>
                <input type="text" name="name" value="<%= customer.getName()%>" required />

                <label>Email:</label>
                <input type="email" name="email" value="<%= customer.getEmail()%>" readonly />

                <label>Contact Number:</label>
                <input type="text" name="contactNo" value="<%= customer.getContactNo() != null ? customer.getContactNo() : ""%>" />

                <label>Address:</label>
                <textarea name="address"><%= customer.getAddress() != null ? customer.getAddress() : ""%></textarea>

                <input type="submit" value="Update Profile" />
            </form>
        </div>
    </main>
</body>
<%@ include file="../includes/footer.jsp" %>
</html>
