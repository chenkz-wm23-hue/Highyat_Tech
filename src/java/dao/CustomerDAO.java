/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Customer;
import util.DBUtil;

import java.sql.*;
import java.util.*;

/**
 *
 * @author sohty
 */
public class CustomerDAO {

    public CustomerDAO() {
    }

    public void addCustomer(Customer cust) {
        String sql = "INSERT INTO CUSTOMER (NAME, EMAIL, PASSWORD, CREATED_AT, CONTACT_NO, ADDRESS) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cust.getName());
            stmt.setString(2, cust.getEmail());
            stmt.setString(3, cust.getPassword());
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.setString(5, cust.getContactNo());
            stmt.setString(6, cust.getAddress());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER";

        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer cust = new Customer();
                cust.setId(rs.getInt("ID"));
                cust.setName(rs.getString("NAME"));
                cust.setEmail(rs.getString("EMAIL"));
                cust.setPassword(rs.getString("PASSWORD"));
                cust.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                cust.setContactNo(rs.getString("CONTACT_NO"));
                cust.setAddress(rs.getString("ADDRESS"));
                list.add(cust);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Customer getCustomerByEmailAndPassword(String email, String password) {
        Customer cust = null;
        String sql = "SELECT * FROM CUSTOMER WHERE EMAIL =? AND PASSWORD =?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cust = new Customer();
                    
                    cust.setId(rs.getInt("ID"));
                    cust.setName(rs.getString("NAME"));
                    cust.setEmail(rs.getString("EMAIL"));
                    cust.setPassword(rs.getString("PASSWORD"));
                    cust.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                    cust.setContactNo(rs.getString("CONTACT_NO"));
                    cust.setAddress(rs.getString("ADDRESS"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cust;
    }
    
    public Customer getCustomerByEmail(String email) {
        Customer cust = null;
        String sql = "SELECT * FROM CUSTOMER WHERE EMAIL =?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cust = new Customer();
                    
                    cust.setId(rs.getInt("ID"));
                    cust.setName(rs.getString("NAME"));
                    cust.setEmail(rs.getString("EMAIL"));
                    cust.setPassword(rs.getString("PASSWORD"));
                    cust.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                    cust.setContactNo(rs.getString("CONTACT_NO"));
                    cust.setAddress(rs.getString("ADDRESS"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cust;
    }

    public boolean updateProfile(Customer customer) {
    String sql = "UPDATE CUSTOMER SET NAME = ?, CONTACT_NO = ?, ADDRESS = ? WHERE EMAIL = ?";
    try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, customer.getName());
        stmt.setString(2, customer.getContactNo());
        stmt.setString(3, customer.getAddress());
        stmt.setString(4, customer.getEmail());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public boolean resetPassword(String email, String newPassword) {
    String sql = "UPDATE CUSTOMER SET PASSWORD = ? WHERE EMAIL = ?";
    try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newPassword);
        stmt.setString(2, email);

        int rows = stmt.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    public boolean deleteCustomerByEmail(String email) {
    String sql = "DELETE FROM CUSTOMER WHERE EMAIL = ?";
    try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
}
