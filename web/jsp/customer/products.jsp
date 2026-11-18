<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Highyat - Products</title>
        <%@ include file="../includes/head.jsp" %>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            String added = (String) session.getAttribute("added");

        %>
    </head>
    <body>

        <%@ include file="../includes/header.jsp" %>

        <main class="container">
            <!-- Display Success Message if Item Added -->
            <% if ("true".equals(added)) { %>
            <p style="color: green; font-weight: bold;">Product added to cart!</p>
            <%
                session.removeAttribute("added");
                added = "false";}%>

            <!-- Product Filter Form -->
            <form method="get" action="<%= request.getContextPath()%>/productServlet" style="margin-bottom: 20px;">
                <label for="category">Filter by Category:</label>
                <select name="category" id="category" onchange="this.form.submit()">
                    <option value="">All</option>
                    <option value="Mouse" <%= "Mouse".equals(request.getParameter("category")) ? "selected" : ""%>>Mouse</option>
                    <option value="Keyboard" <%= "Keyboard".equals(request.getParameter("category")) ? "selected" : ""%>>Keyboard</option>
                    <option value="Microphone" <%= "Microphone".equals(request.getParameter("category")) ? "selected" : ""%>>Microphone</option>
                    <option value="Monitor" <%= "Monitor".equals(request.getParameter("category")) ? "selected" : ""%>>Monitor</option>
                    <option value="Laptop" <%= "Laptop".equals(request.getParameter("category")) ? "selected" : ""%>>Laptop</option>
                    <option value="Accessories" <%= "Accessories".equals(request.getParameter("category")) ? "selected" : ""%>>Accessories</option>
                </select>
            </form>

            <h2>Available Products</h2>

            <%
                if (products == null || products.isEmpty()) {
            %>
            <p>No products found.</p>
            <%
            } else {
            %>
            <div style="display: flex; flex-wrap: wrap; gap: 20px;">
                <% for (Product product : products) {%>
                <div class="featured" style="width: 300px;">
                    <img src="<%= request.getContextPath()%>/assets/images/<%= product.getImageUrl()%>" 
                         alt="<%= product.getName()%>" style="width: 100%; height: auto;">

                    <h3><%= product.getName()%></h3>
                    <p><%= product.getDescription()%></p>
                    <p><strong>RM <%= String.format("%.2f", product.getPrice())%></strong></p>
                    <p>Stock: <%= product.getStock()%></p>

                    <% if (product.getStock() > 0 && product.isActive()) {%>
                    <form method="post" action="<%= request.getContextPath()%>/addToCartServlet">
                        <input type="hidden" name="productId" value="<%= product.getId()%>">
                        <input type="hidden" name="price" value="<%= product.getPrice()%>">
                        <label>Quantity:
                            <input type="number" name="quantity" value="1" min="1" required>
                        </label>
                        <button type="submit" class="checkout-btn">Add to Cart</button>
                    </form>
                    <% } else { %>
                    <button class="checkout-btn" disabled>Out of Stock</button>
                    <% } %>
                </div>
                <% } %>
            </div>
            <% }%>
        </main>
    </body>
    <%@ include file="../includes/footer.jsp" %>
</html>
