package Servlet;

import Model.StudentAttendance;
import Model.AttendanceStatus;
import Repository.Repository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/attendance/create")
public class Create extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("action", "add");
        req.getRequestDispatcher("/test.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        StudentAttendance st = new StudentAttendance();
        st.setStudentName(req.getParameter("studentName"));
        st.setClassDate(Date.valueOf(req.getParameter("classDate")).toLocalDate());
        st.setStatus(AttendanceStatus.valueOf(req.getParameter("status")));
        new Repository().addStudent(st);
        res.sendRedirect(req.getContextPath() + "/attendance");
    }
}