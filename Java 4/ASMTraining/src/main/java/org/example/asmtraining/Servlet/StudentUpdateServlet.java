package org.example.asmtraining.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Repository.StudentDetailRepository;
import org.example.asmtraining.Repository.StudentUpdateRepository;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/students/update")
public class StudentUpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        request.setAttribute("student", new StudentDetailRepository().getStudentDetail(id));
        request.setAttribute("action", "update");
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Student student = new Student();
        student.setStudentCode(request.getParameter("studentCode"));
        student.setFullName(request.getParameter("fullName"));
        student.setEmail(request.getParameter("email"));
        student.setPhone(request.getParameter("phone"));
        student.setDob(Date.valueOf(request.getParameter("dob")).toLocalDate());
        student.setGender(request.getParameter("gender"));
        student.setMajor(request.getParameter("major"));
        student.setGpa(Double.valueOf(request.getParameter("gpa")));
        new StudentUpdateRepository().updateStudent(id, student);
        response.sendRedirect(request.getContextPath() + "/students");
    }
}