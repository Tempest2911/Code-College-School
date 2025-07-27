package Servlet;

import Model.Classes;
import Model.GymMember;
import Repository.ClassesDetailRepo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.List;
@WebServlet("/classes/detail")
public class ClassesDetailServlet extends HttpServlet {
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        ClassesDetailRepo repo = new ClassesDetailRepo();

        // Lấy thông tin lớp
        List<Classes> classInfo = repo.getClassesDetail(id);

        // Lấy danh sách học viên đã đăng ký lớp đó
        List<GymMember> members = repo.getMembersClasses(id);

        // Đẩy dữ liệu lên JSP
        req.setAttribute("classInfo", classInfo);      // có thể là list 1 phần tử
        req.setAttribute("dsMembers", members);
        req.setAttribute("action", "classesDetail");

        req.getRequestDispatcher("/members.jsp").forward(req, resp);
    }
}
