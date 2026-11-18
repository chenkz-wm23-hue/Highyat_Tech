package controller;

import dao.OrderDAO;
import dao.OrderItemDAO;
import model.Order;
import model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {

    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    @Override
    public void init() throws ServletException {
        // Initialize the DAO classes
        orderDAO = new OrderDAO();
        orderItemDAO = new OrderItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderIdStr = request.getParameter("orderId");

        if (orderIdStr == null || orderIdStr.isEmpty()) {
            // Redirect to cart page if no orderId is provided
            response.sendRedirect(request.getContextPath() + "/cartServlet");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdStr);
            Order order = orderDAO.getOrderById(orderId);

            if (order == null) {
                // Redirect to error page if no order is found
                response.sendRedirect(request.getContextPath() + "/jsp/customer/error.jsp");
                return;
            }

            // Fetch order items for the order
            List<OrderItem> orderItems = orderItemDAO.getOrderItemsByOrderId(orderId);

            if (orderItems.isEmpty()) {
                // If no items are found in the order, show a message and forward to order summary or cart page
                request.setAttribute("error", "No items found in this order.");
                request.getRequestDispatcher("/jsp/customer/cart.jsp").forward(request, response);
                return;
            }

            // Set the order and order items as request attributes for JSP
            request.setAttribute("order", order);
            request.setAttribute("orderItems", orderItems);

            // Forward to order details page
            request.getRequestDispatcher("/jsp/customer/orderDetails.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Redirect to error page if any exception occurs
            response.sendRedirect(request.getContextPath() + "/jsp/customer/error.jsp");
        }
    }
}
