package controller;

import dao.ProductDAO;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();  // Directly use ProductDAO to fetch products
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String category = request.getParameter("category");

        try {
            List<Product> products;

            if (category != null && !category.isEmpty()) {
                // Fetch products by category
                products = productDAO.getProductsByCategory(category);
            } else {
                // Fetch all products
                products = productDAO.getAllProducts();
            }

            request.setAttribute("products", products);
            
            request.getRequestDispatcher("/jsp/customer/products.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // If an error occurs, redirect to error page
            response.sendRedirect(request.getContextPath() + "/jsp/customer/error.jsp");
        }
    }
}
