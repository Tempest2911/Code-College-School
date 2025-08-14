package servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Books;
import model.BorrowRequests;
import model.Users;
import repository.BooksRepository;
import repository.BorrowRequestsRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/borrow_requests")
public class YCMServlet extends HttpServlet {

    private BorrowRequestsRepository borrowRequestsRepo = new BorrowRequestsRepository();
    private BooksRepository booksRepo = new BooksRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy thông tin user từ session
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Users currentUser = (Users) session.getAttribute("currentUser");

        // Lấy danh sách yêu cầu mượn của user
        List<BorrowRequests> requests = borrowRequestsRepo.findByUserId(currentUser.getId());

        req.setAttribute("requests", requests);
        req.getRequestDispatcher("/user/DSmuon.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nhận bookId từ form
        String bookIdStr = req.getParameter("bookId");
        if (bookIdStr == null || bookIdStr.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/user/home");
            return;
        }

        // Lấy thông tin user từ session
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Users currentUser = (Users) session.getAttribute("currentUser");

        int bookId = Integer.parseInt(bookIdStr);
        Books book = booksRepo.findById(bookId);

        if (book == null || book.getQuantity() <= 0) {
            // Sách không tồn tại hoặc hết số lượng
            req.setAttribute("error", "Sách đã hết hoặc không tồn tại.");
            doGet(req, resp);  // Hiển thị lại danh sách yêu cầu với lỗi
            return;
        }

        // Tạo yêu cầu mượn mới
        BorrowRequests request = BorrowRequests.builder()
                .user(currentUser)
                .book(book)
                .status("PENDING")
                .build();

        boolean saved = borrowRequestsRepo.save(request);

        if (saved) {
            req.setAttribute("message", "Đã gửi yêu cầu mượn sách thành công.");
        } else {
            req.setAttribute("error", "Có lỗi xảy ra khi gửi yêu cầu.");
        }

        doGet(req, resp);  // Hiển thị lại trang với message hoặc error
    }
}
