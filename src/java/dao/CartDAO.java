package dao;

import model.Cart;
import util.DBUtil;

import java.sql.*;

public class CartDAO {

    public Cart findByCustomerId(int customerId) throws Exception {
        String sql = "SELECT * FROM CART WHERE CUSTOMER_ID = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRowToCart(rs);
            }
        }
        return null;
    }

    public Cart createCartForCustomer(int customerId) throws Exception {
        String sql = "INSERT INTO CART (CUSTOMER_ID) VALUES (?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, customerId);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                return new Cart(newId, customerId, new Timestamp(System.currentTimeMillis()));
            }
        }
        return null;
    }

    private Cart mapRowToCart(ResultSet rs) throws SQLException {
        return new Cart(
                rs.getInt("ID"),
                rs.getInt("CUSTOMER_ID"),
                rs.getTimestamp("CREATED_AT")
        );
    }
}
