package Lab6.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter({
        "/admin/*",
        "/account/change-password",
        "/account/edit-profile",
        "/video/like/*",
        "/video/share/*"
})
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Check login
        Object user = (session != null) ? session.getAttribute("user") : null;
// Inside doFilter() before redirecting to /login
        if (user == null) {
            // Save the original requested URL
            String originalUrl = req.getRequestURI();
            String queryString = req.getQueryString();
            if (queryString != null) {
                originalUrl += "?" + queryString;
            }
            session = req.getSession(true);
            session.setAttribute("redirectAfterLogin", originalUrl);

            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Check admin role for /admin/*
        String uri = req.getRequestURI();
        if (uri.startsWith(req.getContextPath() + "/admin/")) {
            // Assuming user object has a getRole() method
            String role = (String) session.getAttribute("role");
            if (role == null || !role.equals("admin")) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}