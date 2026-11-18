<%@page import="model.CartItem"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Payment - Highyat</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/style.css">
    </head>
    <body class="with-sidebar">

        <%@ include file="../includes/header.jsp" %>

        <main class="container">
            <h2>Payment Method</h2>

            <form action="<%= request.getContextPath()%>/checkoutServlet" method="post">
                <label><input type="radio" name="paymentMethod" value="Credit Card" required> Credit Card</label><br>
                <label><input type="radio" name="paymentMethod" value="Online Banking"> Online Banking</label><br>
                <label><input type="radio" name="paymentMethod" value="Cash on Delivery"> Cash on Delivery</label><br><br>

                <button type="submit" class="checkout-btn">Confirm and Place Order</button>
            </form>
        </main>

        <%@ include file="../includes/footer.jsp" %>

    </body>
</html>
