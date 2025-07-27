package Servlet;

import Model.GymMember;
import Repository.GymMemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet("/gym")
public class GymMemberServlet extends HttpServlet {
    private final GymMemberRepository repo = new GymMemberRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<GymMember> students = repo.getGymMember();
            req.setAttribute("action", "list");
            req.setAttribute("dsGymMember", students);
            req.getRequestDispatcher("gym.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(); // log ra console
            resp.setContentType("text/plain");
            resp.getWriter().write("ERROR: " + e.getMessage());
        }
    }

}


