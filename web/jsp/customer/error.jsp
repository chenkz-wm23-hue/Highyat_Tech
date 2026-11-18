<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Error - Highyat</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/style.css">
    </head>
    <body class="with-sidebar">

        <%@ include file="../includes/header.jsp" %>

        <main class="container">
            <h2>Something went wrong</h2>
            <p>We're sorry, but an unexpected error occurred.</p>

            <%
                Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
                if (e != null) {
            %>
            <h4>Error Details:</h4>
            <pre><%= e.getMessage()%></pre>
            <% }%>
            <br>
            <a href="<%= request.getContextPath()%>/jsp/customer/home.jsp" class="checkout-btn">Back to Home</a>
        </main>

        <%@ include file="../includes/footer.jsp" %>

    </body>
</html>
