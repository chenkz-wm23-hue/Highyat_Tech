/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Customer;

@WebServlet("/resetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");

        boolean success = customerDAO.resetPassword(email, newPassword);

        if (success) {
            response.sendRedirect("jsp/customer/login.jsp?status=success");
        } else {
            response.sendRedirect("jsp/customer/login.jsp?status=fail");
        }
    }
}
