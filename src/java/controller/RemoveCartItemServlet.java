package controller;

import dao.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/removeCartItemServlet")
public class RemoveCartItemServlet extends HttpServlet {

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

            int itemId = Integer.parseInt(request.getParameter("itemId"));

            cartService.deleteById(itemId);

            session.removeAttribute("cartItems");
            response.sendRedirect(request.getContextPath() + "/cartServlet");

        } catch (Exception e) {
            e.printStackTrace();

            response.sendRedirect(request.getContextPath() + "/jsp/customer/error.jsp");
        }
    }
}
