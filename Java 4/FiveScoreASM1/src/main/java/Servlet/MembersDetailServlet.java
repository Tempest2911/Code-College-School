package Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
@WebServlet("/members/detail")
public class MembersDetailServlet extends HttpServlet {
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));

        req.setAttribute("dsMembers", new Repository.MembersDetailRepo().getMembersDetail(id));
        req.setAttribute("action", "detail");
        req.getRequestDispatcher("/members.jsp").forward(req, resp);
    }
}
