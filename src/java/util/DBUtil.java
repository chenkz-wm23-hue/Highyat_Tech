/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.*;

/**
 *
 * @author sohty
 */
public class DBUtil {

    static final String host = "jdbc:derby://localhost:1527/highyat_tech";
    static final String user = "nbuser";
    static final String password = "nbuser";

//    static {
//        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(host, user, password);
    }

}
