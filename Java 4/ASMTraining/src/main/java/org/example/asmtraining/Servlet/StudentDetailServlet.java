package org.example.asmtraining.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.asmtraining.Repository.StudentDetailRepository;
import org.example.asmtraining.Repository.InternshipRepository;

import java.io.IOException;

@WebServlet("/students/detail")
public class StudentDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        StudentDetailRepository studentRepo = new StudentDetailRepository();
        InternshipRepository internshipRepo = new InternshipRepository();
        request.setAttribute("student", studentRepo.getStudentDetail(id));
        request.setAttribute("internships", internshipRepo.getInternshipsByStudentId(id));
        request.setAttribute("action", "detail");
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
}