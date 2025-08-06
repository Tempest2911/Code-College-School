package Lab6.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin".equals(username)){
            if ("123".equals(password)){
                HttpSession session = req.getSession(true);
                session.setAttribute("user", username);
                session.setAttribute("role", "admin"); // or "user"

                String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
                if (redirectUrl != null) {
                    session.removeAttribute("redirectAfterLogin");
                    resp.sendRedirect(req.getContextPath() + redirectUrl);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/");
                }
                return;
            }else {
                req.setAttribute("error", "Invalid admin password");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
        }

        // Các username khác: cho vào role "user" miễn mật khẩu bất kỳ (giả lập không kiểm tra)
        if (username != null && !username.isEmpty()) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", username);
            session.setAttribute("role", "user");

            String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
            if (redirectUrl != null) {
                session.removeAttribute("redirectAfterLogin");
                resp.sendRedirect(req.getContextPath() + redirectUrl);
            } else {
                resp.sendRedirect(req.getContextPath() + "/");
            }
            return;
        }

        // Nếu không nhập username → lỗi
        req.setAttribute("error", "Username cannot be empty");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}