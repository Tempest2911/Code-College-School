package org.example.asmtraining.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Repository.StudentAddRepository;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/students/add")
public class StudentAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("action", "add");
        request.getRequestDispatcher("/students.jsp").forward(request, response);
        // hoặc forward tới /students-add.jsp nếu bạn đã tách file
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = new Student();
        student.setStudentCode(request.getParameter("studentCode"));
        student.setFullName(request.getParameter("fullName"));
        student.setEmail(request.getParameter("email"));
        student.setPhone(request.getParameter("phone"));
        student.setDob(Date.valueOf(request.getParameter("dob")).toLocalDate());
        student.setGender(request.getParameter("gender"));
        student.setMajor(request.getParameter("major"));
        student.setGpa(Double.valueOf(request.getParameter("gpa")));

        new StudentAddRepository().addStudent(student);

        response.sendRedirect(request.getContextPath() + "/students");
    }
}
