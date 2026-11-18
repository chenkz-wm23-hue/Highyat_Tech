package controller;

import dao.UserDAO;
import model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.login(email, password);

            if (user != null && (user.getRole().equals("admin") || user.getRole().equals("staff"))) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("role", user.getRole());
                session.setAttribute("userName", user.getName());

                // ✅ Redirect to dashboard, not login page
                response.sendRedirect(request.getContextPath() + "/jsp/admin/dashboard.jsp");
            } else {
                // On failure, go back to login
                response.sendRedirect(request.getContextPath() + "/jsp/admin/userLogin.jsp?error=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/admin/userLogin.jsp?error=true");
        }
    }
}
