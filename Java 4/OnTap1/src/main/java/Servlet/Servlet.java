package Servlet;

import Model.SanPham;
import Model.LoaiSp;
import Model.SinhVien;
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

@WebServlet(name = "SanPhamServlet", value = {
        "/sanpham/hien-thi",
        "/sanpham/add",
        "/sanpham/update",
        "/sanpham/viewUpdate",
        "/sanpham/delete",
        "/sanpham/soft",
        "/sanpham/search",
        "/login",
        "/api/sinhvien/get-all"
})
public class Servlet extends HttpServlet {

    private Repository repository = new Repository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("hien-thi")) {
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

            req.setAttribute("danhSach", repository.getAllPaged(page, pageSize));
            req.setAttribute("listSP", repository.getLoaiSanPham());
            req.setAttribute("action", "hien-thi");
            req.setAttribute("page", page);
            req.setAttribute("totalPages", totalPages);
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);

        } else if (uri.contains("delete")) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            repository.delete(id);
            resp.sendRedirect("/sanpham/hien-thi");

        } else if (uri.contains("viewUpdate")) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            SanPham sp = repository.getOne(id);
            req.setAttribute("listSP", repository.getLoaiSanPham());
            req.setAttribute("sp", sp);
            req.setAttribute("action", "hien-thi-update");
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);

        } else if (uri.contains("login")) {
            req.setAttribute("action", "login");
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);

        } else if (uri.contains("api/sinhvien/get-all")) {

            List<SinhVien> list = new ArrayList<>();
            list.add(new SinhVien("SV01", "Nguyen Van A", 18, true, "CNTT"));
            list.add(new SinhVien("SV02", "Nguyen Van B", 20, false, "TKDH"));
            list.add(new SinhVien("SV03", "Nguyen Van C", 15, true, "MKT"));
            list.add(new SinhVien("SV04", "Nguyen Van D", 11, false, "CNTT"));
            list.add(new SinhVien("SV05", "Nguyen Van E", 19, true, "TKDH"));

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            Gson gson = new Gson();
            String json = gson.toJson(list);
            resp.getWriter().write(json);

        } else if (uri.contains("soft")) {
            req.setAttribute("danhSach", repository.soft());
            req.setAttribute("action", "hien-thi");
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);

        } else if (uri.contains("search")) {
            String keyword = req.getParameter("keyword");
            req.setAttribute("danhSach", repository.search(keyword));
            req.setAttribute("action", "hien-thi");
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("add")) {
            String ma = req.getParameter("ma");
            Integer idLoaiSp = Integer.valueOf(req.getParameter("idLoaiSp"));
            LoaiSp loaiSp = repository.getLoaiSPID(idLoaiSp);
            String ten = req.getParameter("ten");
            String moTa = req.getParameter("mota");

            SanPham obj = new SanPham(null, ma, ten, moTa, null, null, null, loaiSp, 1);
            repository.add(obj);
            resp.sendRedirect("/sanpham/hien-thi");

        } else if (uri.contains("update")) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            String ma = req.getParameter("ma");
            Integer idLoaiSp = Integer.valueOf(req.getParameter("idLoaiSp"));
            LoaiSp loaiSp = repository.getLoaiSPID(idLoaiSp);
            String ten = req.getParameter("ten");
            String moTa = req.getParameter("mota");

            SanPham obj = new SanPham(id, ma, ten, moTa, null, null, null, loaiSp, 1);
            repository.update(obj);
            resp.sendRedirect("/sanpham/hien-thi");

        } else if (uri.contains("login")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (username == null || username.isBlank() || password == null || password.isBlank()) {
                req.setAttribute("error", "Ko duoc de trong");
                req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
                return;
            } else if (!username.equals("ptpm") || !password.equals("123456")) {
                req.setAttribute("error", "Kiem tra lai thong tin");
                req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
                return;
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                resp.sendRedirect("/sanpham/hien-thi");
            }
        }
    }


}
