package org.example.asmtraining.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.asmtraining.Repository.StudentByMajorRepository;

import java.io.IOException;

@WebServlet("/students/by-major")
public class StudentByMajorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String major = request.getParameter("major");
        request.setAttribute("dsStudent", new StudentByMajorRepository().getStudentsByMajor(major));
        request.setAttribute("action", "major");
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
}


