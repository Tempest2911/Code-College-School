package Servlet;

import Repository.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/members/detail")
public class MemberDetail extends HttpServlet {

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Repository repository = new Repository();
        request.setAttribute("member", repository.getMemberDetail(id));
        request.setAttribute("borrowBooks", repository.getBorrowBooks(id));
        request.setAttribute("action", "detailMember");
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
