package Servlet;

import Repository.BookRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home/searchTitle")
public class SearchByTitleBook extends HttpServlet {
    BookRepo bookRepo = new BookRepo();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        req.setAttribute("books", bookRepo.searchByTitle(name));
        req.setAttribute("action", "searchTitle");
        req.getRequestDispatcher("/asm.jsp").forward(req, resp);
    }
}
