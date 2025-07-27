package Servlet;

import Model.GymMember;
import Repository.MembersListRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/members")
public class MembersListServlet extends HttpServlet {

public final MembersListRepository repo = new MembersListRepository();

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {

        java.util.List<GymMember> members = repo.getAllMembers();
        req.setAttribute("action", "listMembers");
        req.setAttribute("dsMembers", members);
        req.getRequestDispatcher("/members.jsp").forward(req, resp);
    }
}
