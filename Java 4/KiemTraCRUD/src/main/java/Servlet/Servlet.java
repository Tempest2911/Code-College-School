package Servlet;

import Model.Destination;
import Repository.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Servlet", value = {
        "/destination/display",
        "/destination/add",
        "/destination/update",
        "/destination/viewUpdate",
        "/destination/delete",
        "/destination/soft",
        "/destination/search"
})
public class Servlet extends HttpServlet {

    Repository repository = new Repository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("display")) {
            req.setAttribute("danhSach", repository.getAll());
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
        } else if (uri.contains("delete")) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            repository.delete(id);
            resp.sendRedirect("/destination/display");
        } else if (uri.contains("viewUpdate")) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            Destination destination = repository.getOne(id);
            req.setAttribute("destination", destination);
            req.getRequestDispatcher("/update.jsp").forward(req, resp);
        } else if (uri.contains("soft")) {
            req.setAttribute("danhSach", repository.soft());
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
        }else if (uri.contains("search")) {
           String name = req.getParameter("name");
            req.setAttribute("danhSach", repository.search(name));
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("add")) {
            String name = req.getParameter("name");
            String country = req.getParameter("country");
            Integer budgetEst = Integer.valueOf(req.getParameter("budgetEst"));
            Boolean isVisited = Boolean.valueOf(req.getParameter("isVisited"));

            Destination destination = new Destination(null, name, country, budgetEst, isVisited);
            repository.add(destination);

            resp.sendRedirect("/destination/display");
        } else if (uri.contains("update")) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            String country = req.getParameter("country");
            Integer budgetEst = Integer.valueOf(req.getParameter("budgetEst"));
            Boolean isVisited = Boolean.valueOf(req.getParameter("isVisited"));

            Destination destination = new Destination(id, name, country, budgetEst, isVisited);
            repository.update(destination);
            resp.sendRedirect("/destination/display");
        }
    }
}
