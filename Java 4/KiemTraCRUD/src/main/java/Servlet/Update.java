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

@WebServlet("/destination/update")
public class Update extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("destination", new Repository().getDestinationById(id));
        req.setAttribute("action", "update");
        req.getRequestDispatcher("/sigma.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Destination st = new Destination();
        st.setId(Integer.parseInt(req.getParameter("id")));
        st.setName(req.getParameter("name")); // sửa lại đúng tên input
        st.setCountry(req.getParameter("country"));
        st.setBudgetEst(Integer.parseInt(req.getParameter("budgetEst")));

        String isVisitedParam = req.getParameter("isVisited");
        st.setIsVisited(isVisitedParam != null && isVisitedParam.equals("true"));

        new Repository().update(st);
        res.sendRedirect(req.getContextPath() + "/destination");
    }
}
