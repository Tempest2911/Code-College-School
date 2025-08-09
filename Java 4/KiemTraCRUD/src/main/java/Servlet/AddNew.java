package Servlet;

import Model.Destination;
import Repository.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/destination/add")
public class AddNew extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("action", "add");
        req.getRequestDispatcher("/sigma.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        String budgetStr = req.getParameter("budgetEst");
        String isVisitedParam = req.getParameter("isVisited");

        // Validate
        if (name == null || name.trim().isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tên không được để trống.");
            return;
        }

        int budgetEst;
        try {
            budgetEst = Integer.parseInt(budgetStr);
            if (budgetEst < 0) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ngân sách phải ≥ 0.");
                return;
            }
        } catch (NumberFormatException e) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ngân sách không hợp lệ.");
            return;
        }

        // Nếu hợp lệ, xử lý
        Destination st = new Destination();
        st.setName(name);
        st.setCountry(country);
        st.setBudgetEst(budgetEst);
        st.setIsVisited("true".equals(isVisitedParam));

        new Repository().add(st);
        res.sendRedirect(req.getContextPath() + "/destination");
    }
}
