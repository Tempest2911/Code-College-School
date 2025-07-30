package Servlet;

import Model.Member;
import Repository.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

@WebServlet("/members")
public class AllMembersSvlet extends HttpServlet {
    Repository repo = new Repository();
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {

        List<Member> members = repo.getAllMember();
        request.setAttribute("action", "listMembers");
        request.setAttribute("dsMembers", members);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
