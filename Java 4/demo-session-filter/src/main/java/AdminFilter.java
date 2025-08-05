package org.example.buoi10;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();

        String currentUser = (String) session.getAttribute("currentUser");

        if (currentUser == null || currentUser.isEmpty()) {
            servletResponse.getWriter().println("Please login first!");
        } else if (!currentUser.equals("admin")) {
            servletResponse.getWriter().println("You are not ADMIN");
        } else {
            // ✅ User is admin, continue processing
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}