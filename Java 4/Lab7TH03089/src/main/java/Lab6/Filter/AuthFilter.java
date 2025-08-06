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

        String uri = req.getRequestURI();
        Object user = (session != null) ? session.getAttribute("user") : null;

        // Require login for all filtered URLs
        if (user == null) {
            session = req.getSession(true);
            String originalUrl = uri + (req.getQueryString() != null ? "?" + req.getQueryString() : "");
            session.setAttribute("redirectAfterLogin", originalUrl);
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Require admin role for /admin/*
        if (uri.startsWith(req.getContextPath() + "/admin/")) {
            String role = (String) session.getAttribute("role");
            if (!"admin".equals(role)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Can I have Admin?");
                return;
            }
        }

        // Require user role for /account/change-password, /account/edit-profile, /video/like/*, and /video/share/*
        if (uri.startsWith(req.getContextPath() + "/account/") ||
            uri.startsWith(req.getContextPath() + "/video/like/") ||
            uri.startsWith(req.getContextPath() + "/video/share/")) {
            String role = (String) session.getAttribute("role");
            if (!"user".equals(role) && !"admin".equals(role)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Can I have User?");
                return;
            }
        }


        chain.doFilter(request, response);
    }
}