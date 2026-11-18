/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Customer;

/**
 *
 * @author sohty
 */
@WebServlet("/registerCustomer")
public class RegisterCustomer extends HttpServlet {
    
    CustomerDAO customerDAO = new CustomerDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        Customer cust = new Customer();
        cust.setName(request.getParameter("name"));
        cust.setContactNo(request.getParameter("contact_no"));
        cust.setEmail(request.getParameter("email"));
        cust.setPassword(request.getParameter("password"));
        
        customerDAO.addCustomer(cust);
        response.sendRedirect("jsp/customer/login.jsp");
    }
}
