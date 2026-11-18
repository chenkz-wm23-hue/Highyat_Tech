package controller;

import dao.CartService;
import model.Cart;
import model.CartItem;
import model.Order;
import dao.OrderDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import util.OrderStatus;

@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {

    private CartService cartService;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        cartService = new CartService();
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/customer/login.jsp");
            return;
        }

        try {
            int customerId = (int) session.getAttribute("customerId");

            // Get the cart
            Cart cart = cartService.getOrCreateCartByCustomerId(customerId);

            // Get cart items
            List<CartItem> cartItems = cartService.getCartItemsByCartId(cart.getId());

            // If cart is empty, show error and redirect to cart
            if (cartItems.isEmpty()) {
                request.setAttribute("error", "Your cart is empty.");
                request.getRequestDispatcher("/cartServlet").forward(request, response);
                return;
            }


            Order order = new Order();
            order.setCustomerId(customerId);
            order.setPaymentMethod(request.getParameter("paymentMethod"));  // Get payment method (e.g., "Credit Card")
            order.setOrderStatus(OrderStatus.PENDING_PAYMENT);  // Default status: Pending
            order.setTotalAmount((BigDecimal) session.getAttribute("totalAmount"));

            // Fetch the customer's address from the CUSTOMER table
            String customerAddress = fetchCustomerAddress(customerId);  // Fetch the address based on customer ID
            if (customerAddress == null) {
                request.setAttribute("error", "No address found for the customer.");
                request.getRequestDispatcher("/cartServlet").forward(request, response);
                return;
            }

            
            orderDAO.insertOrder(order);  
            int orderId = orderDAO.getOrderIdByCustomerId(customerId);  

            // Insert the order items using the orderId
            orderDAO.insertOrderItems(orderId, cartItems);  // Insert the order items with the orderId

            // Clear the cart after placing the order
            cartService.clearCart(cart.getId());

            // Redirect to order confirmation page
            response.sendRedirect(request.getContextPath() + "/jsp/customer/checkout-success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/jsp/customer/error.jsp");
        }
    }

    // Method to fetch the customer's address from the CUSTOMER table
    private String fetchCustomerAddress(int customerId) throws Exception {
        // Retrieve the address from the CUSTOMER table using the customer ID
        String address = cartService.getCustomerAddressByCustomerId(customerId);
        return address;
    }
}
