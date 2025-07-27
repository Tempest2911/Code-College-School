package org.example.asmtraining.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.asmtraining.Repository.StudentDeleteRepository;

import java.io.IOException;

@WebServlet("/students/delete")
public class StudentDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        new StudentDeleteRepository().deleteStudent(id);
        response.sendRedirect(request.getContextPath() + "/students");
    }
}