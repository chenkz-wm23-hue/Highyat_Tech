/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Customer;

@WebServlet("/deleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/customer/login.jsp");
            return;
        }

        String email = (String) session.getAttribute("email");

        boolean success = customerDAO.deleteCustomerByEmail(email);

        session.invalidate(); // Logout user

        if (success) {
            response.sendRedirect(request.getContextPath() + "/jsp/customer/login.jsp?status=deleted");
        } else {
            response.sendRedirect(request.getContextPath() + "/jsp/customer/profile.jsp");
        }
    }
}

