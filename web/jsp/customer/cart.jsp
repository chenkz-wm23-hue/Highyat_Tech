<%@page import="java.text.DecimalFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CartItem" %>

<%
    List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");

    BigDecimal grandTotal = new BigDecimal("0");
    BigDecimal shipping = new BigDecimal("10");
    BigDecimal total = new BigDecimal("0");
    DecimalFormat df = new DecimalFormat("#0.00");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Highyat - Your Cart</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/style.css">
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                background-color: #f5f5f5;
            }

            .cart-container {
                max-width: 1200px;
                margin: 0 auto;
                display: flex;
                gap: 20px;
                flex-wrap: wrap;
            }

            .cart-items {
                flex: 2;
                max-width: 690px;
                max-height: 680px;
                background: white;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }

            .cart-item {
                display: flex;
                align-items: center;
                padding: 15px 0;
                border-bottom: 1px solid #eee;
            }

            .cart-item img {
                width: 80px;
                height: 80px;
                object-fit: cover;
                border-radius: 4px;
                margin-right: 20px;
            }

            .item-details {
                flex: 1;
            }

            .item-details h3 {
                font-size: 16px;
                margin-bottom: 5px;
            }

            .item-details p {
                color: #666;
                font-size: 14px;
            }

            .item-quantity {
                display: flex;
                align-items: center;
                gap: 10px;
                margin: 0 20px;
            }

            .item-quantity button {
                width: 30px;
                height: 30px;
                border: 1px solid #ddd;
                background: #f9f9f9;
                cursor: pointer;
                border-radius: 4px;
            }

            .item-price {
                font-weight: bold;
                min-width: 80px;
                text-align: right;
            }

            .remove-item {
                color: #ff4444;
                cursor: pointer;
                margin-left: 20px;
            }

            .cart-summary {
                flex: 1;
                background: white;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                height: fit-content;
            }

            .cart-summary h2 {
                font-size: 20px;
                margin-bottom: 20px;
            }

            .summary-row {
                display: flex;
                justify-content: space-between;
                margin-bottom: 10px;
                font-size: 14px;
            }

            .summary-total {
                font-weight: bold;
                font-size: 16px;
                padding-top: 10px;
                border-top: 1px solid #eee;
                margin-top: 10px;
            }

            .checkout-btn {
                width: 100%;
                padding: 15px;
                background: #28a745;
                color: white;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
                margin-top: 20px;
                transition: background 0.3s;
            }

            .checkout-btn:hover {
                background: #218838;
            }

            .empty-cart {
                text-align: center;
                padding: 40px;
                color: #666;
            }

            @media (max-width: 768px) {
                .cart-container {
                    flex-direction: column;
                }

                .cart-item {
                    flex-wrap: wrap;
                }

                .item-quantity {
                    margin: 10px 0;
                }

                .item-price {
                    text-align: left;
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>

        <%@ include file="../includes/header.jsp" %>

        <main class="cart-container">
            <section class="cart-items">
                <h2>Your Cart</h2>
                <p>Review your selected items before checking out.</p>
                <h3>Items in Your Cart</h3>
                <%
                    if (cartItems == null || cartItems.isEmpty()) {
                %>
                <p class="empty-cart">Your cart is currently empty.</p>
                <%
                } else {
                %>
                <table style="width: 100%; border-collapse: collapse;">
                    <thead>
                        <tr style="border-bottom: 1px solid #ccc;">
                            <th style="padding: 10px;">Product</th>
                            <th style="padding: 10px;">Price</th>
                            <th style="padding: 10px;">Quantity</th>
                            <th style="padding: 10px;">Total</th>
                            <th style="padding: 10px;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (CartItem item : cartItems) {
                                BigDecimal itemPrice = item.getPrice() != null ? item.getPrice() : BigDecimal.ZERO;
                                BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
                                BigDecimal itemTotal = itemPrice.multiply(quantity);
                                grandTotal = grandTotal.add(itemTotal);
                        %>

                        <tr style="border-bottom: 1px solid #eee;">
                            <td style="padding: 10px;"><%= item.getProductName()%></td>
                            <td style="padding: 10px;">RM <%= df.format(item.getPrice())%></td>
                            <td style="padding: 10px;"><%= item.getQuantity()%></td>
                            <td style="padding: 10px;">RM <%= df.format(itemTotal)%></td>
                            <td style="padding: 10px;">
                                <form method="post" action="<%= request.getContextPath()%>/removeCartItemServlet">
                                    <input type="hidden" name="itemId" value="<%= item.getId()%>">
                                    <button type="submit" class="checkout-btn" style="background-color:#ef4444;">Remove</button>
                                </form>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% } %>
            </section>

            <% if (cartItems != null && !cartItems.isEmpty()) {
                    total = total.add(grandTotal).add(shipping);
                    session.setAttribute("totalAmount", total);
            %>
            <section class="cart-summary">
                <h3>Summary</h3>
                <p><strong>Subtotal:</strong> RM <%= df.format(grandTotal)%></p>
                <p><strong>Shipping:</strong> RM <%= df.format(shipping)%></p>
                <p><strong>Total:</strong> RM <%= df.format(total)%></p>
                <br>
                <form action="<%= request.getContextPath()%>/jsp/customer/payment.jsp" method="post">
                    <button class="checkout-btn">Proceed to Checkout</button>
                </form>
            </section>
            <% }%>
        </main>

        <%@ include file="../includes/footer.jsp" %>

    </body>
</html>
