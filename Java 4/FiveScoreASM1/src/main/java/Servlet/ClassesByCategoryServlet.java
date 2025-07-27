package Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


@WebServlet("/classes/by-category")
public class ClassesByCategoryServlet extends HttpServlet {
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {
        String category = req.getParameter("category");
        req.setAttribute("dsClasses", new Repository.ClassesByCategoryRepo().getClassesCategory(category));
        req.setAttribute("action", "category");
        req.getRequestDispatcher("/members.jsp").forward(req, resp);
    }
}
