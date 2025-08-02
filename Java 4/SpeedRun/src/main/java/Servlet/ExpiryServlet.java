package Servlet;

import Model.Product;
import Repository.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products/prepare-expire")
public class ExpiryServlet extends HttpServlet {
    private Repository repo = new Repository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> expireSoon = repo.getProductsByExpiryDate();
        req.setAttribute("products", expireSoon);
        req.setAttribute("action", "prepare-expire");
        req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
    }
}