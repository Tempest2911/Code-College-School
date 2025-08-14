package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;
import repository.UsersRepository;
import util.PasswordUtil;

import java.io.IOException;

@WebServlet({"/login", "/admin/login"})

public class LoginServlet extends HttpServlet {
    private UsersRepository usersRepo = new UsersRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị trang login (cả admin & student dùng chung trang login này hoặc khác cũng được)
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Users user = usersRepo.findByEmail(email); // lấy user theo email
        if (user == null) {
            request.setAttribute("error", "Email hoặc mật khẩu không đúng");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        String stored = user.getPassword();
        boolean ok = false;

        // Nếu stored là BCrypt hash → verify
        if (stored != null && (stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$"))) {
            ok = PasswordUtil.verify(password, stored);
        } else {
            // Nếu DB đang có users cũ lưu plaintext (chưa hash) — tạm hỗ trợ để migrate:
            // (1) so sánh plaintext, nếu đúng -> re-hash và update DB
            if (stored != null && stored.equals(password)) {
                ok = true;
                // re-hash và lưu lại
                user.setPassword(PasswordUtil.hash(password));
                usersRepo.update(user); // cần implement update trong UsersRepository
            }
        }

        if (!ok) {
            request.setAttribute("error", "Email hoặc mật khẩu không đúng");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // đã ok: đặt session và chuyển hướng theo role
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", user);
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/admin/homeadmin.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/user/homeuser.jsp");
        }
    }

}