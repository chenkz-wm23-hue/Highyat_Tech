package dao;

import model.User;
import util.DBUtil;

import java.sql.*;

public class UserDAO {

    public User login(String email, String password) throws Exception {
        String sql = "SELECT * FROM \"USER\" WHERE EMAIL = ? AND PASSWORD = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("NAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setRole(rs.getString("ROLE"));
                return user;
            }
        }
        return null;
    }

    public User findByEmail(String email) throws Exception {
        String sql = "SELECT * FROM \"USER\" WHERE EMAIL = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("NAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setRole(rs.getString("ROLE"));
                user.setContactNo(rs.getString("CONTACT_NO"));
                user.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                user.setLastSignIn(rs.getTimestamp("LAST_SIGN_IN"));
                return user;
            }
        }
        return null;
    }

}
