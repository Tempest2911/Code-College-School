package Servlet;

import Repository.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/books/by-category")
public class BookByCato extends HttpServlet {

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
        // Logic to retrieve books by category and forward to the JSP page
        String category = request.getParameter("category");
        request.setAttribute("dsbooks", new Repository().getBookByCategory(category));
        request.setAttribute("action", "by-category");
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
