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

        // TODO: Validate username and password with your UserDAO
        // Example:
        // User user = userDAO.findByUsername(username);
        // if (user != null && user.getPassword().equals(password)) { ... }

        boolean loginSuccess = /* your authentication logic here */;
        if (loginSuccess) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            session.setAttribute("role", /* user's role, e.g., "admin" or "user" */);

            // Redirect to original URL if present
            String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
            if (redirectUrl != null) {
                session.removeAttribute("redirectAfterLogin");
                resp.sendRedirect(redirectUrl);
            } else {
                resp.sendRedirect(req.getContextPath() + "/");
            }
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}