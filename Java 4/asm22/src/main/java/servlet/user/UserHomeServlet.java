package servlet.user;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Books;
import repository.BooksRepository;

import java.io.IOException;
import java.util.List;
@WebServlet("/user/books")

public class UserHomeServlet extends HttpServlet {

    private BooksRepository booksRepo = new BooksRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        int page = 1;
        int pageSize = 5;

        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException ignored) {}
        }

        List<Books> books;
        long totalBooks;

        if (keyword != null && !keyword.trim().isEmpty()) {
            keyword = keyword.trim();
            books = booksRepo.searchByTitleOrAuthorPaginated(keyword, page, pageSize);
            totalBooks = booksRepo.countSearch(keyword);
        } else {
            books = booksRepo.findAllPaginated(page, pageSize);
            totalBooks = booksRepo.countAll();
        }

        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

        req.setAttribute("books", books);
        req.setAttribute("keyword", keyword);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/user/homeuser.jsp").forward(req, resp);
    }



}
