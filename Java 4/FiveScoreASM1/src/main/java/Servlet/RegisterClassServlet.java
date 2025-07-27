package Servlet;

import Model.ClassRegistration;
import Model.Classes;
import Model.GymMember;
import Repository.ClassRegistrationRepo;
import Util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/register")
public class RegisterClassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("action", "register");
        request.getRequestDispatcher("/members.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int classId = Integer.parseInt(request.getParameter("classId"));

        try (var session = HibernateUtil.getSession()) {
            session.beginTransaction();

            var member = session.get(GymMember.class, memberId);
            var clazz = session.get(Classes.class, classId);

            if (member == null || clazz == null) {
                request.setAttribute("error", "Không tìm thấy hội viên hoặc lớp.");
                request.getRequestDispatcher("/members.jsp").forward(request, response);
                return;
            }

            var registration = new ClassRegistration();
            registration.setMember(member);
            registration.setClassField(clazz);

            session.save(registration);
            session.getTransaction().commit();
        }

        response.sendRedirect(request.getContextPath() + "/classes");
    }
}


