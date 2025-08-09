package Servlet;

import Model.Destination;
import Repository.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/destination")
public class GetAll extends HttpServlet {

    private Repository repo = new Repository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Destination> destination = repo.getAll();
        req.setAttribute("destination", destination);
        req.setAttribute("action", "listDestination");
        req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
    }
}
