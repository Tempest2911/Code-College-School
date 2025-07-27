package Servlet;

import Model.Classes;
import Repository.ClassesAvailableRepo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/classes/available")
public class ClassesAvailableServlet extends HttpServlet {
    public final ClassesAvailableRepo repo = new ClassesAvailableRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws jakarta.servlet.ServletException, IOException {

        List<Classes> members = repo.getClassesAvailable();
        req.setAttribute("action", "available");
        req.setAttribute("dsClasses", members);
        req.getRequestDispatcher("/members.jsp").forward(req, resp);
    }
}
