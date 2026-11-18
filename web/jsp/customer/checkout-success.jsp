<%@page import="java.math.BigDecimal"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CartItem" %>

<%
    List<CartItem> orderItems = (List<CartItem>) session.getAttribute("orderSummaryItems");
    Double subtotal = (Double) (session.getAttribute("orderSubtotal") != null ? ((BigDecimal)session.getAttribute("orderSubtotal")).doubleValue() : 0.0);
    Double shipping = (Double) (session.getAttribute("orderShipping") != null ? ((BigDecimal)session.getAttribute("orderShipping")).doubleValue() : 0.0);
    Double tax = (Double) (session.getAttribute("orderTax") != null ? ((BigDecimal)session.getAttribute("orderTax")).doubleValue() : 0.0);
    Double total = (Double) (session.getAttribute("orderTotal") != null ? ((BigDecimal)session.getAttribute("orderTotal")).doubleValue() : 0.0);
    String payment = (String) session.getAttribute("orderPaymentMethod");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Order Placed - Highyat</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>

<%@ include file="../includes/header.jsp" %>

<main class="container">
    <h2>✅ Your Order Has Been Placed!</h2>
    <p>Thank you for shopping with us. Below is your order summary:</p>

    <% if (orderItems != null && !orderItems.isEmpty()) { %>
    <table style="width:100%; border-collapse:collapse;">
        <thead>
            <tr>
                <th style="text-align:left; padding:10px;">Product</th>
                <th>Quantity</th>
                <th>Unit Price</th>
                <th>Subtotal</th>
            </tr>
        </thead>
        <tbody>
        <% for (CartItem item : orderItems) { %>
            <tr>
                <td style="padding:10px;"><%= item.getProductName() %></td>
                <td><%= item.getQuantity() %></td>
                <td>RM <%= String.format("%.2f", item.getPrice()) %></td>
                <td>RM <%= String.format("%.2f", item.getPrice().doubleValue() * item.getQuantity()) %></td>
            </tr>
        <% } %>
        </tbody>
    </table>

    <hr style="margin: 20px 0;">

    <div style="text-align:right;">
        <p>Subtotal: RM <%= String.format("%.2f", subtotal) %></p>
        <p>Shipping: RM <%= String.format("%.2f", shipping) %></p>
        <p>Sales Tax (6%): RM <%= String.format("%.2f", tax) %></p>
        <h3>Total: RM <%= String.format("%.2f", total) %></h3>
        <p><strong>Payment Method:</strong> <%= payment %></p>
    </div>
    <% } else { %>
        <p>No order details found.</p>
    <% } %>

    <div style="margin-top: 30px;">
        <a href="<%= request.getContextPath() %>/productServlet" class="checkout-btn">Continue Shopping</a>
    </div>
</main>

<%@ include file="../includes/footer.jsp" %>
</body>
</html>
