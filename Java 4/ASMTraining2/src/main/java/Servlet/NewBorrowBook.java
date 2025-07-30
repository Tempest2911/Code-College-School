package Servlet;

import Model.Book;
import Model.BorrowRecord;
import Model.Member;
import Repository.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
@WebServlet("/borrow")
public class NewBorrowBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("action", "borrow-form");
        req.getRequestDispatcher("/books.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int memberId = Integer.parseInt(req.getParameter("memberId"));
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            LocalDate borrowDate = LocalDate.parse(req.getParameter("borrowDate"));
            LocalDate dueDate = LocalDate.parse(req.getParameter("dueDate"));

            BorrowRecord record = new BorrowRecord();
            record.setMember(new Member());
            record.getMember().setId(memberId);

            record.setBook(new Book());
            record.getBook().setId(bookId);

            record.setBorrowDate(borrowDate);
            record.setDueDate(dueDate);
            record.setStatus("Borrowed");

            new Repository().addBorrowRecord(record);


        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi khi mượn sách: " + e.getMessage());
            req.setAttribute("action", "borrow-form");
            req.getRequestDispatcher("/books.jsp").forward(req, resp);
        }
    }
}

