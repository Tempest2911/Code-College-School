package Servlet;

import Model.Classes;
import Model.GymMember;
import Repository.ClassesListRepo;
import Repository.MembersListRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

@WebServlet("/classes")
public class ClassesListServlet extends HttpServlet {

    public final ClassesListRepo repo = new ClassesListRepo();

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {

        List<Classes> classes = repo.getAllClass();
        req.setAttribute("action", "listClasses");
        req.setAttribute("dsClasses", classes);
        req.getRequestDispatcher("/members.jsp").forward(req, resp);
    }
}

