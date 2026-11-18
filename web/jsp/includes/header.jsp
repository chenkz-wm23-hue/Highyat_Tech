<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="logo">
        <h1>Highyat</h1>
    </div>

    <nav>
        <a href="<%= request.getContextPath()%>/jsp/customer/home.jsp">Home</a>
        <a href="<%= request.getContextPath()%>/productServlet">Products</a>
        <% if ("customer".equals((String) session.getAttribute("role"))) {%>
        <a href="<%= request.getContextPath()%>/cartServlet">
            Cart
            <% Integer count = (Integer) session.getAttribute("cartCount"); %>
            <% if (count != null && count > 0) {%>
            <span class="cart-badge"><%= count%></span>
            <% }%>
        </a>
        <a href="<%= request.getContextPath()%>/profileServlet">Profile</a>
        <a href="<%= request.getContextPath()%>/logout">Logout</a>
        <% } else {%>
        <a href="<%= request.getContextPath()%>/jsp/customer/login.jsp">Login</a>
        <a href="<%= request.getContextPath()%>/jsp/customer/register.jsp">Register</a>
        <% }%>
    </nav>

</header>

