package controller;

import dao.CartService;
import model.Cart;
import model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {

    private CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/customer/login.jsp");
            return;
        }

        try {
            int customerId = (int) session.getAttribute("customerId");

            Cart cart = cartService.getOrCreateCartByCustomerId(customerId);

            List<CartItem> cartItems = cartService.getCartItemsByCartId(cart.getId());

            session.setAttribute("cartItems", cartItems);

            request.getRequestDispatcher("/jsp/customer/cart.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/jsp/customer/error.jsp");
        }
    }
}
