package Lab6.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

@WebServlet({"/video/list", "/video/detail/*", "/video/like/*", "/video/share/*"})
public class VideoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        req.getRequestDispatcher("/page.jsp").forward(req, resp);
    }
}