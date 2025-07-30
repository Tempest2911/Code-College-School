package Servlet;

import Model.BorrowRecord;
import Repository.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/borrow/overdue")
public class OverdueBorrowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BorrowRecord> overdueList = new Repository().getOverdueRecords();
        request.setAttribute("action", "overdue");
        request.setAttribute("records", overdueList);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
