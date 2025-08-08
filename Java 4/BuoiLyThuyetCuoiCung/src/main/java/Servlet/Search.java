package Servlet;

import Model.StudentAttendance;
import Repository.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

@WebServlet("/search")
public class Search extends HttpServlet {

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
        Repository repo = new Repository();
        String keyword = request.getParameter("keyword");
        List<StudentAttendance> dsStudent = repo.searchByName(keyword);
        request.setAttribute("dsStudent", dsStudent);
        request.setAttribute("action", "search");
        request.getRequestDispatcher("/search.jsp").forward(request, response);
    }
}
