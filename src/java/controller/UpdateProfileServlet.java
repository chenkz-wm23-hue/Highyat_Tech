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

@WebServlet("/updateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/customer/login.jsp");
            return;
        }

        
        String contactNo = request.getParameter("contactNo");
        String address = request.getParameter("address");

        
        Customer customer = (Customer) session.getAttribute("customer");

        
        customer.setContactNo(contactNo);
        customer.setAddress(address);

        
        boolean updated = customerDAO.updateProfile(customer);

        if (updated) {
            
            session.setAttribute("customer", customer);
            response.sendRedirect(request.getContextPath() + "/profileServlet");
        } else {
            request.setAttribute("error", "Failed to update profile. Please try again.");
            request.getRequestDispatcher("/jsp/customer/editProfile.jsp").forward(request, response);
        }
    }
}