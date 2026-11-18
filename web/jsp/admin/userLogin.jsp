<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>

        <style>
            .login-box {
                max-width: 400px;
                margin: 80px auto;
                padding: 30px;
                background-color: #ffffff;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                border-radius: 12px;
            }

            .login-box h2 {
                text-align: center;
                margin-bottom: 25px;
            }

            .login-box input[type="email"],
            .login-box input[type="password"] {
                width: 100%;
                padding: 10px;
                margin-top: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 6px;
            }

            .login-box button {
                width: 100%;
                padding: 10px;
                background-color: #111827;
                color: white;
                font-weight: bold;
                border: none;
                border-radius: 6px;
                cursor: pointer;
            }

            .login-box button:hover {
                background-color: #1f2937;
            }

            .login-box .error {
                color: red;
                text-align: center;
                margin-bottom: 15px;
            }
        </style>
        <title>Admin/Staff Login - Highyat</title>
        <%@ include file="../includes/head.jsp" %>
    </head>
    <body>
        <%@ include file="../includes/header-staff-admin.jsp" %>

        <main>
            <div class="login-box">
                <h2>Admin/Staff Login</h2>

                <% if ("true".equals(request.getParameter("error"))) { %>
                <div class="error">Invalid email or password. Try again.</div>
                <% }%>

                <form method="post" action="<%= request.getContextPath()%>/UserLoginServlet">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" required>

                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" required>

                    <button type="submit">Login</button>
                </form>
            </div>
        </main>

    </body>
</html>
