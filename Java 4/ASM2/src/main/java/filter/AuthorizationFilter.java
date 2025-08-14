package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*") // Áp dụng cho tất cả request
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();

        // Nếu chưa đăng nhập
        if (session == null || session.getAttribute("currentUser") == null) {
            // Cho phép đến các trang login, register, hoặc tài nguyên công khai
            if (uri.contains("/login") || uri.contains("/register") || uri.contains("/public/")) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
            return;
        }

        // User đã đăng nhập
        Object userObj = session.getAttribute("currentUser");
        // Giả sử model Users có phương thức getRole()
        String role = null;
        if (userObj != null) {
            role = (String) req.getSession().getAttribute("userRole");
            if (role == null && userObj instanceof model.Users) {
                role = ((model.Users) userObj).getRole();
                req.getSession().setAttribute("userRole", role); // cache role trong session
            }
        }

        // Kiểm tra phân quyền dựa theo URL
        if (uri.startsWith(req.getContextPath() + "/admin")) {
            // Yêu cầu role phải là admin
            if (!"ADMIN".equalsIgnoreCase(role)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang này!");
                return;
            }
        } else if (uri.startsWith(req.getContextPath() + "/user")) {
            // Yêu cầu role phải là student
            if (!"STUDENT".equalsIgnoreCase(role)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang này!");
                return;
            }
        }

        // Nếu không bị chặn, cho phép tiếp tục
        chain.doFilter(request, response);
    }
}
