package dao;

import java.sql.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Cart;
import model.CartItem;

import java.util.List;
import util.DBUtil;

public class CartService {

    private final CartDAO cartDAO = new CartDAO();
    private final CartItemDAO cartItemDAO = new CartItemDAO();

    // Get existing cart or create a new one for the customer
    public Cart getOrCreateCartByCustomerId(int customerId) throws Exception {
        Cart cart = cartDAO.findByCustomerId(customerId);
        if (cart == null) {
            cart = cartDAO.createCartForCustomer(customerId);
        }
        return cart;
    }

    // Get all items in the cart by cart ID
    public List<CartItem> getCartItemsByCartId(int cartId) throws Exception {
        return cartItemDAO.findItemsByCartId(cartId);
    }

    public void updateQuantity(int itemId, int newQuantity) throws Exception {
        cartItemDAO.updateQuantity(itemId, newQuantity);
    }

    public void insertItem(int cartId, int productId, int quantity, BigDecimal price) throws Exception {
        cartItemDAO.insertItem(cartId, productId, quantity, price);
    }

    public void deleteById(int itemId) throws Exception {
        String sql = "DELETE FROM CART_ITEM WHERE ID = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        }
    }

    public String getCustomerAddressByCustomerId(int customerId) throws Exception {
        String sql = "SELECT ADDRESS FROM CUSTOMER WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId); // Set customerId as the parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("ADDRESS"); // Fetch and return the address
            }
        }
        return null; // Return null if no address is found
    }

    public void clearCart(int cartId) throws Exception {
        cartItemDAO.deleteItemsByCartId(cartId);
    }

}
