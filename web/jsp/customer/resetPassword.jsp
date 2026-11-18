<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <style>
            .form-box {
                max-width: 400px;
                margin: 40px auto;
                padding: 25px;
                background: #ffffff;
                border-radius: 12px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            }

            .form-box h2 {
                text-align: center;
                margin-bottom: 20px;
                color: #1e293b;
            }

            .form-box label,
            .form-box input,
            .form-box button,
            .form-box .info-text {
                display: block;
                width: 100%;
                margin-top: 10px;
            }

            .form-box input {
                padding: 10px;
                border-radius: 8px;
                border: 1px solid #cbd5e1;
            }

            .form-box button {
                background-color: #1e40af;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                margin-top: 20px;
            }

            .form-box .info-text {
                text-align: center;
                margin-top: 15px;
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




        </style>
        <title>Highyat - Login</title>
        <%@ include file="../includes/head.jsp" %>
        <%
            String role = (String) session.getAttribute("role");
            if (role != null) {
                response.sendRedirect(request.getContextPath() + "/jsp/customer/home.jsp");
                return;
            }
        %>
    </head>
    <body>
        <%@ include file="../includes/header.jsp" %>
        <main>
            <div class="form-box">
                <h2>Reset Password</h2>

                <% if (request.getParameter("status") != null && request.getParameter("status").equals("success")) { %>
                <div class="info-text">Password updated successfully!</div>
                <% } else if (request.getParameter("status") != null && request.getParameter("status").equals("fail")) { %>
                <div class="error-message">Failed to reset password. Check your email.</div>
                <% }%>

                <form action="<%= request.getContextPath()%>/resetPasswordServlet" method="post">
                    <label for="email">Email</label>
                    <input type="email" name="email" required />

                    <label for="newPassword">New Password</label>
                    <input type="password" name="newPassword" required />

                    <button type="submit">Reset Password</button>
                </form>
            </div>

        </main>
    </body>
    <%@ include file="../includes/footer.jsp" %>
</html>
