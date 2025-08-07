package Servlet;

import Model.AttendanceStatus;
import Model.StudentAttendance;
import Repository.Repository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/attendance/update")
public class Update extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("student", new Repository().getStudentById(id));
        req.setAttribute("action", "update");
        req.getRequestDispatcher("/test.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        StudentAttendance st = new StudentAttendance();
        st.setId(Integer.parseInt(req.getParameter("id")));
        st.setStudentName(req.getParameter("studentName"));
        st.setClassDate(Date.valueOf(req.getParameter("classDate")).toLocalDate());
        st.setStatus(AttendanceStatus.valueOf(req.getParameter("status")));
        new Repository().updateStudent(st);
        res.sendRedirect(req.getContextPath() + "/attendance");
    }
}