package Servlet;

import Repository.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/members/top-borrowers")
public class Top5 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Repository repository = new Repository();
        List<Object[]> topMembers = repository.getTop5Borrow(); // row[0] = GymMember, row[1] = Long

        req.setAttribute("topBorrow", topMembers);
        req.setAttribute("action", "top-attendance");
        req.getRequestDispatcher("/books.jsp").forward(req, resp);
    }

}
