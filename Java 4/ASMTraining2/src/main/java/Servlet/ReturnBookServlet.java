package Servlet;

import Model.BorrowRecord;
import Repository.Repository;
import Util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/return")
public class ReturnBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int borrowId = Integer.parseInt(request.getParameter("id"));
        new Repository().returnBook(borrowId);

        response.setContentType("text/plain");
        response.getWriter().write("Book returned successfully.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int borrowId = Integer.parseInt(request.getParameter("id"));
        new Repository().returnBook(borrowId);
    }
}


