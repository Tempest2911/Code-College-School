package Servlet;

import Model.BorrowRecord;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer borrowId = Integer.parseInt(request.getParameter("id"));
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            BorrowRecord record = session.get(BorrowRecord.class, borrowId);
            if (record != null) {
                record.setReturnDate(LocalDate.now());
                record.setStatus("Returned");
                session.update(record);
            }

            tx.commit();
        }
        response.sendRedirect(request.getContextPath() + "/borrow/list");
    }
}

