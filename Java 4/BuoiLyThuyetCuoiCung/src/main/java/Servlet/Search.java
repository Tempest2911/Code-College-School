package Servlet;

import Model.StudentAttendance;
import Repository.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

@WebServlet("/attendance/search")
public class Search extends HttpServlet {
    Repository repo = new Repository();
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {

        String keyword = request.getParameter("keyword");
        List<StudentAttendance> dsStudent = repo.searchProducts(keyword);
        request.setAttribute("dsStudent", dsStudent);
        request.setAttribute("action", "search");
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
}
