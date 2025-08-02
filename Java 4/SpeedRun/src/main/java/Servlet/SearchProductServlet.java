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

@WebServlet("/products/search")
public class SearchProductServlet extends HttpServlet {
    private Repository repo = new Repository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Product> results = repo.searchProducts(keyword);
        req.setAttribute("products", results);
        req.setAttribute("action", "searchProducts");
        req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
    }
}
