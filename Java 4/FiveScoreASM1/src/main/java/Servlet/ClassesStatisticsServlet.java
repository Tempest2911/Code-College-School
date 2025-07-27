package Servlet;

import Repository.ClassesStatisticsRepo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/statistics/classes/by-category")
public class ClassesStatisticsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws jakarta.servlet.ServletException, IOException {

        ClassesStatisticsRepo repo = new ClassesStatisticsRepo();
        List<Object[]> stats = repo.getClassesStatistics();

        req.setAttribute("stats", stats);
        req.setAttribute("action", "statistics");
        req.getRequestDispatcher("/members.jsp").forward(req, resp);
    }
}
