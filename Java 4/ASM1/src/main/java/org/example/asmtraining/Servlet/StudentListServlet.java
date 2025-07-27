package org.example.asmtraining.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Repository.StudentListRepository;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {
    private final StudentListRepository repo = new StudentListRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Student> students = repo.getAllStudent();
        req.setAttribute("action", "list");
        req.setAttribute("dsStudent", students);
        req.getRequestDispatcher("/students.jsp").forward(req, resp);
    }
}
