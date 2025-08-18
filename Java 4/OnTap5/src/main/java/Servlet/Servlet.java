package Servlet;

import Model.LoaiPhongBan;
import Model.PhongBan;
import Repository.Repository;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PhongBanServlet", value = {
        "/phongban/hien-thi",
        "/phongban/add",
        "/phongban/update",
        "/phongban/viewUpdate",
        "/phongban/delete",
        "/login",
        "/api/sinhvien/get-all"
})
public class Servlet extends HttpServlet {

    private Repository repository = new Repository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("hien-thi")) {
            HienThi(req, resp);
        } else if (uri.contains("delete")) {
            Delete(req, resp);
        } else if (uri.contains("viewUpdate")) {
            ViewUpdate(req, resp);
        }
//        else if (uri.contains("login")) {
//            LoginUI(req, resp);
//        } else if (uri.contains("api/sinhvien/get-all")) {
//            Ajax(req, resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("add")) {
            Add(req, resp);
        } else if (uri.contains("update")) {
            Update(req, resp);
        }
//        else if (uri.contains("login")) {
//            Login(req, resp);
//        }
    }

    public void HienThi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int pageSize = 5;
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            } catch (NumberFormatException ignored) {
            }
        }
        long totalItems = repository.countAll();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        req.setAttribute("danhSach", repository.getAll(page, pageSize));
        req.setAttribute("listSP", repository.getLoaiPhongBan());
        req.setAttribute("action", "hien-thi");
        req.setAttribute("page", page);
        req.setAttribute("totalPages", totalPages);
        req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
    }

    public void Add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        Integer idPb = Integer.valueOf(req.getParameter("idPb"));
        LoaiPhongBan phongBan = repository.getLoaiPhongBanID(idPb);

        PhongBan obj = new PhongBan(id, phongBan, ma, ten, null, null);

        repository.add(obj);
        resp.sendRedirect("/phongban/hien-thi");
    }

    public void ViewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        PhongBan sp = repository.getOne(id);
        req.setAttribute("listSP", repository.getLoaiPhongBan());
        req.setAttribute("sp", sp);
        req.setAttribute("action", "update");
        req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
    }

    public void Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        Integer idPb = Integer.valueOf(req.getParameter("idPb"));
        LoaiPhongBan phongBan = repository.getLoaiPhongBanID(idPb);

        PhongBan obj = new PhongBan(id, phongBan, ma, ten, null, null);
        repository.update(obj);
        resp.sendRedirect("/phongban/hien-thi");
    }

    public void Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        repository.delete(id);
        resp.sendRedirect("/phongban/hien-thi");
    }
}
