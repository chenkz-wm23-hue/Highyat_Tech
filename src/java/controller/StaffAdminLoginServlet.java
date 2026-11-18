package controller;

import dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import model.User;

@WebServlet("/staffAdminLoginServlet")
public class StaffAdminLoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO(); // Assumes you have a DAO for user table
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.findByEmail(email);

            if (user != null && user.getPassword().equals(password) &&
                ("staff".equals(user.getRole()) || "admin".equals(user.getRole()))) {

                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("role", user.getRole());

                if ("admin".equals(user.getRole())) {
                    response.sendRedirect("admin/home.jsp");
                } else {
                    response.sendRedirect("staff/home.jsp");
                }

            } else {
                request.setAttribute("error", "Invalid credentials or unauthorized role.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Login failed due to system error.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

