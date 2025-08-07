package Servlet;

import Model.StudentAttendance;
import Repository.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

@WebServlet("/attendance")
public class GetAllTable extends HttpServlet {

    private final Repository repo = new Repository();

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
        List<StudentAttendance> dsStudent = repo.getStudentAttendance();
        request.setAttribute("action", "listTables");
            request.setAttribute("dsStudent", dsStudent);
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
}
