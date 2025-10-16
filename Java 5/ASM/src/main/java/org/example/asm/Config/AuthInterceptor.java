package org.example.asm.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        // Nếu chưa đăng nhập
        if (session == null || session.getAttribute("currentUser") == null) {
            String uri = request.getRequestURI();

            // Cho phép truy cập các trang công khai
            if (uri.contains("/asm/login") || uri.contains("/asm/register")) {
                return true;
            }

            response.sendRedirect("/asm/login");
            return false;
        }

        return true;
    }
}
