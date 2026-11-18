package dao;

import model.Order;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.CartItem;

public class OrderDAO {

    public int insertOrder(Order order) throws Exception {
        String orderSql = "INSERT INTO \"ORDER\" (CUSTOMER_ID, PAYMENT_METHOD, ORDER_STATUS, TOTAL_AMOUNT) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(orderSql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setString(2, order.getPaymentMethod());
            stmt.setString(3, order.getOrderStatus());
            stmt.setBigDecimal(4, order.getTotalAmount());
            stmt.executeUpdate();

        }
        return 0;  // Return 0 if insertion failed (should ideally throw an exception)
    }

    public void insertOrderItems(int orderId, List<CartItem> cartItems) throws Exception {
        String itemSql = "INSERT INTO ORDER_ITEM (ORDER_ID, PRODUCT_ID, QUANTITY, PRICE) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(itemSql)) {
            for (CartItem item : cartItems) {
                stmt.setInt(1, orderId);  // Set the generated orderId
                stmt.setInt(2, item.getProductId());  // Set the productId
                stmt.setInt(3, item.getQuantity());  // Set the quantity
                stmt.setBigDecimal(4, item.getPrice());  // Set the price
                stmt.addBatch();  // Add to batch to insert all items in one batch
            }
            stmt.executeBatch();  // Execute batch insert for all order items
        }
    }
    
    public int getOrderIdByCustomerId(int customerId) throws Exception {
        String sql = "SELECT ID FROM \"ORDER\" WHERE CUSTOMER_ID = ? ";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);  // Set customerId in the query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID");  // Return the order ID
            }
        }
        return -1;  // Return -1 if no order is found for the customer
    }

    // Retrieve an order by its ID
    public Order getOrderById(int orderId) throws Exception {
        String sql = "SELECT * FROM \"ORDER\" WHERE ID = ?";
        Order order = null;

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("ID"));
                order.setCustomerId(rs.getInt("CUSTOMER_ID"));
                order.setPaymentMethod(rs.getString("PAYMENT_METHOD"));
                order.setOrderStatus(rs.getString("ORDER_STATUS"));
                order.setTotalAmount(rs.getBigDecimal("TOTAL_AMOUNT"));
                order.setCreatedAt(rs.getString("CREATED_AT"));
            }
        }

        return order;
    }
}
