package Servlet;

import Repository.TopAttendanceRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet("/members/top-attendance")
public class TopAttendanceServlet extends HttpServlet {

    private final TopAttendanceRepo repo = new TopAttendanceRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Object[]> topMembers = repo.getTop5Attendance(); // row[0] = GymMember, row[1] = Long

        req.setAttribute("topMembers", topMembers);
        req.setAttribute("action", "top-attendance");
        req.getRequestDispatcher("/members.jsp").forward(req, resp);
    }
}
