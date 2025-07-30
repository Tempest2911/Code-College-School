package Servlet;

import Repository.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/books/statistics/by-category")
public class StatisticsBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy danh sách thống kê
        List<Object[]> stats = new Repository().getStatisticsByBook();

        // Chuyển sang đối tượng tạm để tiện hiển thị
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] row : stats) {
            Map<String, Object> stat = new HashMap<>();
            stat.put("category", row[0]);
            stat.put("count", row[1]);
            result.add(stat);
        }

        request.setAttribute("action", "statistics");
        request.setAttribute("stats", result);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
