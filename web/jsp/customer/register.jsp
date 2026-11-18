<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <style>
            .form-box {
                max-width: 450px;
                margin: 40px auto;
                padding: 30px;
                background: #fff;
                border-radius: 12px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            }

            .form-box h2 {
                text-align: center;
                color: #1e293b;
            }

            .form-box label {
                display: block;
                margin-top: 15px;
                font-weight: 600;
            }

            .form-box input {
                width: 100%;
                padding: 10px;
                margin-top: 6px;
                border: 1px solid #cbd5e1;
                border-radius: 8px;
            }

            .form-box button {
                margin-top: 20px;
                width: 100%;
                background-color: #1e40af;
                color: #fff;
                padding: 10px;
                border: none;
                border-radius: 8px;
                font-weight: bold;
                cursor: pointer;
            }

        </style>
        <title>Highyat - Register</title>
        <%@ include file="../includes/head.jsp" %>
        <%
            if ((String) session.getAttribute("role") != null) {
                response.sendRedirect(request.getContextPath() + "/jsp/customer/home.jsp");
                return;
            }

        %>
    </head>
    <body>
        <%@ include file="../includes/header.jsp" %>
        <main>
            <form action="<%= request.getContextPath()%>/registerCustomer" method="post" class="form-box">
                <h2>Customer Registration</h2>

                <label for="name">Name</label>
                <input type="text" name="name" id="name" required />

                <label for="contact_no">Contact Number</label>
                <input type="text" name="contact_no" pattern="[0-9]{10,15}" title="Enter a valid contact number" required>

                <label for="email">Email</label>
                <input type="email" name="email" id="email" required />

                <label for="password">Password</label>
                <input type="password" name="password" id="password" required minlength="6" />

                <label for="confirmPassword">Confirm Password</label>
                <input type="password" name="confirmPassword" id="confirmPassword" required minlength="6" />

                <button type="submit">Register</button>

                <p class="info-text">Already have an account? <a href="login.jsp">Login here</a>.</p>
            </form>

            <%
                String error = request.getParameter("error");
                if (error != null) {
                    switch (error) {
                        case "empty_fields":
                            out.println("<p style='color:red;'>Please fill in all fields.</p>");
                            break;
                        case "invalid_email":
                            out.println("<p style='color:red;'>Invalid email format.</p>");
                            break;
                        case "email_exists":
                            out.println("<p style='color:red;'>Email already registered.</p>");
                            break;
                        case "insert_failed":
                            out.println("<p style='color:red;'>Registration failed. Try again.</p>");
                            break;
                        case "exception":
                            out.println("<p style='color:red;'>A system error occurred. Please contact support.</p>");
                            break;
                    }
                } else if ("success".equals(request.getParameter("register"))) {
                    out.println("<p style='color:green;'>Registration successful! Please login.</p>");
                }
            %>

        </main>
    </body>
    <%@ include file="../includes/footer.jsp" %>
</html>
