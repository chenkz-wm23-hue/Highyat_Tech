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

/**
 *
 * @author sohty
 */
@WebServlet("/loginCustomer")
public class LoginCustomer extends HttpServlet {

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Please enter both email and password.");
            request.getRequestDispatcher("/jsp/customer/login.jsp").forward(request, response);
            return;
        }
        
        Customer customer = customerDAO.getCustomerByEmailAndPassword(email, password);

        if (customer != null) {
            // Create a session and store customer data
            HttpSession session = request.getSession(true);  
            session.setAttribute("email", email);
            session.setAttribute("role", "customer");
            session.setAttribute("customerId", customer.getId());

            response.sendRedirect(request.getContextPath() + "/jsp/customer/home.jsp"); 
        } else {
              response.sendRedirect(request.getContextPath() + "/jsp/customer/login.jsp?error=true");
        }
    }
}
