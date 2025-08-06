import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/nhanVien")
public class NhanVienServlet extends HttpServlet {

    // Hiển thị trang JSP
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/bai1.jsp").forward(req, resp);
    }

    // Trả về JSON khi gọi bằng POST (AJAX)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        out.println("{");
        out.println("\"maNV\": \"TeoNV\",");
        out.println("\"hoTen\": \"Nguyễn Văn Tèo\",");
        out.println("\"gioiTinh\": true,");
        out.println("\"luong\": 950.5");
        out.println("}");
        out.flush();
    }
}