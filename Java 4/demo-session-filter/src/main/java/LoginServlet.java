
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // GET -> Hiển thị trang đăng nhập
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    // POST -> Xử lý đăng nhập
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("tenTaiKhoan");
        String password = req.getParameter("matKhau");

        resp.getWriter().println("<h1>Access DoGet</h1>");
        resp.getWriter().println(username + " - " + password);

        if (username.equals(password)) {
            resp.getWriter().println("<h1 style='color:green'>SUCCESS</h1>");
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", username);
        } else {
            resp.getWriter().println("<h1 style='color:red'>FAILURE</h1>");
        }
    }

}