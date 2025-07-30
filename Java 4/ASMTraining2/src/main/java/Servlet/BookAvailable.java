package Servlet;

import Repository.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/books/available")
public class BookAvailable extends HttpServlet {

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
        // Logic to retrieve available books and forward to the JSP page
        request.setAttribute("dsbooks", new Repository().getBookavailable());
        request.setAttribute("action", "available");
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
