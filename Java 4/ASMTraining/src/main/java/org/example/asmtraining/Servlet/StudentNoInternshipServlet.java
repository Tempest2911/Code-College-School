package org.example.asmtraining.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.asmtraining.Repository.StudentNoInternshipRepository;

import java.io.IOException;

@WebServlet("/students/no-internship")
public class StudentNoInternshipServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsStudent", new StudentNoInternshipRepository().getStudentsNoInternship());
        request.setAttribute("action", "no-internship");
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
}