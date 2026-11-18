package dao;

import model.OrderItem;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    // Insert a new order item into the database
    public void insertOrderItem(OrderItem orderItem) throws Exception {
        String sql = "INSERT INTO ORDER_ITEM (ORDER_ID, PRODUCT_ID, QUANTITY, PRICE) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getProductId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setBigDecimal(4, orderItem.getPrice());
            stmt.executeUpdate();
        }
    }
    
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws Exception {
        String sql = "SELECT oi.ID, oi.ORDER_ID, oi.PRODUCT_ID, oi.QUANTITY, oi.PRICE, p.NAME AS PRODUCT_NAME " +
                     "FROM ORDER_ITEM oi " +
                     "JOIN PRODUCT p ON oi.PRODUCT_ID = p.ID " +
                     "WHERE oi.ORDER_ID = ?";

        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);  // Set the orderId in the query
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("ID"));
                orderItem.setOrderId(rs.getInt("ORDER_ID"));
                orderItem.setProductId(rs.getInt("PRODUCT_ID"));
                orderItem.setQuantity(rs.getInt("QUANTITY"));
                orderItem.setPrice(rs.getBigDecimal("PRICE"));
                // Dynamically fetch product name using the JOIN
                orderItem.setProductName(rs.getString("PRODUCT_NAME"));

                orderItems.add(orderItem);
            }
        }

        return orderItems;
    }
    
}
