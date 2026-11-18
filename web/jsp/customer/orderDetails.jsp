<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Order, model.OrderItem" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Order Details</title>
    </head>
    <body>

        <!-- Display Error Message if No Items in Order -->
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
            <p style="color: red; font-weight: bold;"><%= error %></p>
        <% } %>

        <h2>Order Details</h2>

        <!-- Display Order Details -->
        <p>Order ID: <%= request.getAttribute("order").getId() %></p>
        <p>Customer ID: <%= request.getAttribute("order").getCustomerId() %></p>
        <p>Payment Method: <%= request.getAttribute("order").getPaymentMethod() %></p>
        <p>Order Status: <%= request.getAttribute("order").getOrderStatus() %></p>
        <p>Total Amount: RM <%= request.getAttribute("order").getTotalAmount() %></p>

        <h3>Order Items</h3>
        <% 
            List<OrderItem> orderItems = (List<OrderItem>) request.getAttribute("orderItems");
            if (orderItems != null && !orderItems.isEmpty()) {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (OrderItem item : orderItems) { %>
                        <tr>
                            <td><%= item.getProductId() %></td>  <!-- This can be replaced with product name if needed -->
                            <td><%= item.getQuantity() %></td>
                            <td>RM <%= item.getPrice() %></td>
                            <td>RM <%= item.getPrice().multiply(new BigDecimal(item.getQuantity())) %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No items found in this order.</p>
        <% } %>

        <br>
        <a href="<%= request.getContextPath() %>/orderHistory.jsp">Back to Order History</a>

    </body>
</html>
