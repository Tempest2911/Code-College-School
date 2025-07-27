package Servlet;

import Model.GymMember;
import Repository.MemberActiveRepository;
import Repository.MembersListRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/members/active")
public class MembersActiveServlet extends HttpServlet {

    public final MemberActiveRepository repo = new MemberActiveRepository();

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {

        java.util.List<GymMember> members = repo.getActiveMembers();
        req.setAttribute("action", "active");
        req.setAttribute("dsMembers", members);
        req.getRequestDispatcher("/members.jsp").forward(req, resp);
    }
}