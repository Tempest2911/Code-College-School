package Servlet;

import Model.Book;
import Repository.BookRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    BookRepo bookRepo = new BookRepo();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookRepo.getAll();
        req.setAttribute("books", books);
        req.setAttribute("action", "home");
        req.getRequestDispatcher("/asm.jsp").forward(req, resp);
    }
}
