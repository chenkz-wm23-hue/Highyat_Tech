package dao;

import model.CartItem;
import model.Product;
import util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {

    // Find all items in a cart, including product name
    public List<CartItem> findItemsByCartId(int cartId) throws Exception {
        String sql = "SELECT ci.*, p.NAME AS PRODUCT_NAME "
                + "FROM CART_ITEM ci JOIN PRODUCT p ON ci.PRODUCT_ID = p.ID "
                + "WHERE ci.CART_ID = ?";

        List<CartItem> items = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cartId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("ID"));
                item.setCartId(rs.getInt("CART_ID"));
                item.setProductId(rs.getInt("PRODUCT_ID"));
                item.setQuantity(rs.getInt("QUANTITY"));
                item.setPrice(rs.getBigDecimal("PRICE_AT_ADDITION"));

                Product p = new Product();
                p.setId(rs.getInt("PRODUCT_ID"));
                p.setName(rs.getString("PRODUCT_NAME"));
                item.setProduct(p);

                items.add(item);
            }
        }
        return items;
    }

    // Find specific item in cart (used in AddToCart)
    public CartItem findByCartIdAndProductId(int cartId, int productId) throws Exception {
        String sql = "SELECT * FROM CART_ITEM WHERE CART_ID = ? AND PRODUCT_ID = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cartId);
            stmt.setInt(2, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("ID"));
                item.setCartId(rs.getInt("CART_ID"));
                item.setProductId(rs.getInt("PRODUCT_ID"));
                item.setQuantity(rs.getInt("QUANTITY"));
                item.setPrice(rs.getBigDecimal("PRICE_AT_ADDITION"));
                return item;
            }
        }
        return null;
    }

    // Insert new item
    public void insertItem(int cartId, int productId, int quantity, BigDecimal price) throws Exception {
        String sql = "INSERT INTO CART_ITEM (CART_ID, PRODUCT_ID, QUANTITY, PRICE_AT_ADDITION) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cartId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setBigDecimal(4, price);
            stmt.executeUpdate();
        }
    }

    // Update quantity
    public void updateQuantity(int itemId, int newQuantity) throws Exception {
        String sql = "UPDATE CART_ITEM SET QUANTITY = ? WHERE ID = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newQuantity);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
        }
    }

    // Delete all items in cart (optional for checkout or reset)
    public void deleteItemsByCartId(int cartId) throws Exception {
        String sql = "DELETE FROM CART_ITEM WHERE CART_ID = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cartId);
            stmt.executeUpdate();
        }
    }

    public String getCustomerAddressByCustomerId(int customerId) throws Exception {
        String sql = "SELECT * FROM CUSTOMER_ADDRESS WHERE CUSTOMER_ID = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("ADDRESS_LINE1") + ", " + rs.getString("CITY") + ", " + rs.getString("COUNTRY");
            }
        }
        return null;  // Return null if no address is found
    }

}
