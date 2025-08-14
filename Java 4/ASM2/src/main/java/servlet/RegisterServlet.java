package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;
import repository.UsersRepository;
import util.PasswordUtil;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UsersRepository usersRepo = new UsersRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Check email tồn tại chưa
        if (usersRepo.findByEmail(email) != null) {
            req.setAttribute("error", "Email đã được đăng ký.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // Tạo user mới, role mặc định STUDENT

        Users user = Users.builder()
                .name(name)
                .email(email)
                .password(PasswordUtil.hash(password)) // hash trước khi lưu
                .role("STUDENT")
                .build();


        boolean saved = usersRepo.save(user);

        if (saved) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
