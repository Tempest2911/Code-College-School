package Servlet;

import Repository.Repository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/attendance/delete")
public class DeleteStudentAttendance extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        new Repository().deleteStudent(Integer.valueOf(req.getParameter("id")));
        res.sendRedirect(req.getContextPath() + "/attendance");
    }
}