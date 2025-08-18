package Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter(urlPatterns = {"/demo", "/phieugiamgia/*"})
public class AuthServlet implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String username = (session != null) ? (String) session.getAttribute("username") : null;
        String role = (session != null) ? (String) session.getAttribute("role") : null;
        String uri = request.getRequestURI();

        if (username == null || role == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if ("Admin".equals(role)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if ("Student".equals(role)) {
            if (uri.endsWith("/demo")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "403 - Cấm quyền");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}