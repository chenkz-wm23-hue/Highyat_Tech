package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/adminLogout")
public class AdminLogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // do not create
        if (session != null) {
            session.invalidate(); // destroy session
        }

        // Redirect to admin login page
        response.sendRedirect(request.getContextPath() + "/jsp/admin/userLogin.jsp");
    }
}
