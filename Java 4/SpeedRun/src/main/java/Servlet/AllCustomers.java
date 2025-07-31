package Servlet;

import Model.Customer;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.eclipse.tags.shaded.org.apache.bcel.Repository;
import Repository.Repo;

import java.util.List;

@WebServlet("/customers")
public class AllCustomers extends HttpServlet {

@Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
        // Logic to retrieve all customers and forward to the JSP page
        // Assuming you have a method in Repository to get all customers
        List<Customer> customers = new Repo().getAllBook();
        request.setAttribute("action", "listCustomers");
        request.setAttribute("dscustomers", customers);
        request.getRequestDispatcher("/sigma.jsp").forward(request, response);
    }
}
