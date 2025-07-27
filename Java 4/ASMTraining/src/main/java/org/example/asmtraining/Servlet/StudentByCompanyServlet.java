package org.example.asmtraining.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.asmtraining.Repository.StudentByCompanyRepository;

import java.io.IOException;

@WebServlet("/students/by-company")
public class StudentByCompanyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String company = request.getParameter("company");
        request.setAttribute("dsStudent", new StudentByCompanyRepository().getStudentsByCompany(company));
        request.setAttribute("action", "by-company");
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
}