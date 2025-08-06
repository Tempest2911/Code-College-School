package Lab6.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

@WebServlet({"/account/sign-up", "/account/change-password", "/account/edit-profile"})
public class AccountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        req.getRequestDispatcher("/page.jsp").forward(req, resp);
    }
}