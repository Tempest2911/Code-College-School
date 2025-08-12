package servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Books;
import repository.BooksRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/books")
public class CRUDServlet extends HttpServlet {

    private BooksRepository booksRepo = new BooksRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    Books book = booksRepo.findById(id);
                    if (book != null) {
                        req.setAttribute("editBook", book);
                    }
                } catch (NumberFormatException ignored) {}
            }
        } else if ("delete".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    booksRepo.delete(id);
                    resp.sendRedirect(req.getContextPath() + "/admin/books");
                    return;
                } catch (NumberFormatException ignored) {}
            }
        }
        List<Books> books = booksRepo.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/admin/qlsach.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String idStr = req.getParameter("id");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String quantityStr = req.getParameter("quantity");

        String error = null;
        if (title == null || title.trim().isEmpty()) {
            error = "Tiêu đề sách không được để trống";
        } else if (author == null || author.trim().isEmpty()) {
            error = "Tác giả không được để trống";
        } else if (quantityStr == null || quantityStr.trim().isEmpty()) {
            error = "Số lượng không được để trống";
        }

        int quantity = 0;
        if (error == null) {
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity < 0) {
                    error = "Số lượng phải lớn hơn hoặc bằng 0";
                }
            } catch (NumberFormatException e) {
                error = "Số lượng phải là số nguyên";
            }
        }

        Books book = new Books();
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                book = booksRepo.findById(id);
                if (book == null) {
                    resp.sendRedirect(req.getContextPath() + "/admin/books");
                    return;
                }
            } catch (NumberFormatException ignored) {}
        }

        if (error != null) {
            // Trả về dữ liệu lỗi và giữ lại thông tin đã nhập
            book.setTitle(title);
            book.setAuthor(author);
            book.setQuantity(quantity);
            req.setAttribute("error", error);
            req.setAttribute("editBook", book);

            List<Books> books = booksRepo.findAll();
            req.setAttribute("books", books);

            req.getRequestDispatcher("/admin/qlsach.jsp").forward(req, resp);
            return;
        }

        // Thêm mới hoặc cập nhật
        if (book.getId() != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setQuantity(quantity);
            booksRepo.update(book);
        } else {
            book = Books.builder()
                    .title(title)
                    .author(author)
                    .quantity(quantity)
                    .build();
            booksRepo.save(book);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/books");
    }
}
