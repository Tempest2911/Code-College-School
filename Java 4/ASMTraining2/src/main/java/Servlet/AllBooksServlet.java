package Servlet;

import Model.Book;
import Repository.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.List;


@WebServlet("/books")
public class AllBooksServlet extends HttpServlet {


    private final Repository repo = new Repository();

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
        // Logic to retrieve all books and forward to the JSP page
        List<Book> books = repo.getAllBook();
        request.setAttribute("action", "listBooks");
        request.setAttribute("dsbooks", books);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
