package Servlet;

import Repository.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet("/books/detail")
public class BooksDetail extends HttpServlet {

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Repository repository = new Repository();
            request.setAttribute("book", repository.getBookDetail(id));
            request.setAttribute("borrowRecords", repository.getBorrowRecord(id));
            request.setAttribute("action", "detail");
            request.getRequestDispatcher("/books.jsp").forward(request, response);

    }
}
