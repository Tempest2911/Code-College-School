package Servlet;

import Repository.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/members/active")
public class MemberACtive extends HttpServlet {

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {
        Repository repository = new Repository();
        request.setAttribute("dsMembers", repository.getMemberActive());
        request.setAttribute("action", "active");
        request.getRequestDispatcher("/books.jsp").forward(request, response);

    }
}
