package servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Books;
import model.BorrowRequests;
import repository.BooksRepository;
import repository.BorrowRequestsRepository;
import util.EmailUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/borrow_requests")
public class AdminBorrowRequestsServlet extends HttpServlet {

    private BorrowRequestsRepository borrowRequestsRepo = new BorrowRequestsRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BorrowRequests> requests = borrowRequestsRepo.findAll();
        req.setAttribute("requests", requests);
        req.getRequestDispatcher("/admin/borrow_requests.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String requestIdStr = req.getParameter("requestId");

        if (requestIdStr == null || action == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/borrow_requests");
            return;
        }

        int requestId = Integer.parseInt(requestIdStr);
        BorrowRequests request = borrowRequestsRepo.findById(requestId);

        if (request == null || !"PENDING".equals(request.getStatus())) {
            resp.sendRedirect(req.getContextPath() + "/admin/borrow_requests");
            return;
        }

        try {
            if ("approve".equals(action)) {
                Books book = request.getBook();
                if (book.getQuantity() > 0) {
                    book.setQuantity(book.getQuantity() - 1);
                    BooksRepository booksRepo = new BooksRepository();
                    booksRepo.update(book);

                    request.setStatus("APPROVED");
                    borrowRequestsRepo.update(request);

                    // Gửi email thông báo APPROVED
                    String subject = "Your book request has been approved";
                    String content = "Hello " + request.getUser().getName() + ",<br><br>" +
                            "Good news! Your request to borrow the book <b>" + request.getBook().getTitle() + "</b> has been <b>approved</b>.<br>" +
                            "Please come to the library to collect it.<br><br>" +
                            "Best regards,<br>Library Management";
                    EmailUtil.sendEmail(request.getUser().getEmail(), subject, content);

                } else {
                    req.setAttribute("error", "Cannot approve because the book is out of stock.");
                    doGet(req, resp);
                    return;
                }
            } else if ("reject".equals(action)) {
                request.setStatus("REJECTED");
                borrowRequestsRepo.update(request);

                // Gửi email thông báo REJECTED
                String subject = "Your book request has been rejected";
                String content = "Hello " + request.getUser().getName() + ",<br><br>" +
                        "We’re sorry to inform you that your request to borrow the book <b>" + request.getBook().getTitle() + "</b> has been <b>rejected</b>.<br>" +
                        "Please contact the library for more details.<br><br>" +
                        "Best regards,<br>Library Management";
                EmailUtil.sendEmail(request.getUser().getEmail(), subject, content);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error while processing the request: " + e.getMessage());
            doGet(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/admin/borrow_requests");
    }
}
