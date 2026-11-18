package controller;

import dao.CartService;
import model.Cart;
import model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/addToCartServlet")
public class AddToCartServlet extends HttpServlet {

    private CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CartService();
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

            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            BigDecimal price = new BigDecimal(request.getParameter("price"));

            // Get or create cart
            Cart cart = cartService.getOrCreateCartByCustomerId(customerId);

            // Find if the item already exists in the cart
            CartItem existingItem = null;
            for (CartItem item : cartService.getCartItemsByCartId(cart.getId())) {
                if (item.getProductId() == productId) {
                    existingItem = item;
                    break;
                }
            }

            if (existingItem != null) {
                // If item exists, update quantity
                int newQty = existingItem.getQuantity() + quantity;
                cartService.updateQuantity(existingItem.getId(), newQty);
            } else {
                // If item doesn't exist, insert it
                cartService.insertItem(cart.getId(), productId, quantity, price);
            }

            session.setAttribute("added", "true");
            response.sendRedirect(request.getContextPath() + "/productServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/jsp/customer/error.jsp");
        }
    }
}
