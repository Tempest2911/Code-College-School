package org.example.onthifinaljava4.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter({
        "/san-pham/hien-thi",
        "/san-pham/add",
        "/san-pham/detail",
})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        boolean authenticated = false;
        if (session != null) {
            if (session.getAttribute("username") != null) {
                authenticated = true;
            }
        }

        if (!authenticated) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
