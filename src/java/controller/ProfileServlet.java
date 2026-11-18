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

@WebServlet("/profileServlet")
public class ProfileServlet extends HttpServlet {

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        if (email != null) {
            
            Customer customer = customerDAO.getCustomerByEmail(email);

            if (customer != null) {
                
                session.setAttribute("customer", customer);
                response.sendRedirect(request.getContextPath() + "/jsp/customer/profile.jsp");
                
            } else {
                response.sendRedirect(request.getContextPath() + "/jsp/customer/login.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/jsp/customer/login.jsp");
        }
    }
}
