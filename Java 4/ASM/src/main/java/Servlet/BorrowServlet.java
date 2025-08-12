package Servlet;

import Model.User;
import Repository.BookRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {
    private BookRepo bookRepo = new BookRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        User user = (User) req.getSession().getAttribute("user");

        // Lấy id của user
        int userId = user.getId();

        // Gửi yêu cầu mượn sách với trạng thái PENDING
        bookRepo.createBorrowRequest(userId, bookId);

        // Quay về home
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
