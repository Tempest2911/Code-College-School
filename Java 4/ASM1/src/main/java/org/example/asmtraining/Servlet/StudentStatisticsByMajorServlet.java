package org.example.asmtraining.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.asmtraining.Repository.StudentStatisticsByMajorRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/students/statistics/by-major")
public class StudentStatisticsByMajorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy danh sách thống kê
        List<Object[]> stats = new StudentStatisticsByMajorRepository().getStatisticsByMajor();

        // Chuyển sang đối tượng tạm để tiện hiển thị
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] row : stats) {
            Map<String, Object> stat = new HashMap<>();
            stat.put("major", row[0]);
            stat.put("count", row[1]);
            result.add(stat);
        }

        request.setAttribute("action", "statistics");
        request.setAttribute("stats", result);
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
}