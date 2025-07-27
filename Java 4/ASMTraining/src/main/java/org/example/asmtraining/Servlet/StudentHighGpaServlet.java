package org.example.asmtraining.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.asmtraining.Repository.StudentHighGpaRepository;

import java.io.IOException;

@WebServlet("/students/high-gpa")
public class StudentHighGpaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsStudent", new StudentHighGpaRepository().getHighGpaStudents());
        request.setAttribute("action", "high-gpa");
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
}