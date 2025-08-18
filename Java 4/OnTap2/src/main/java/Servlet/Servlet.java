package Servlet;

import Model.LoaiPhieuGiamGia;
import Model.PhieuGiamGia;
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

@WebServlet(name = "PhieuGiamGiaServlet", value = {
        "/phieugiamgia/hien-thi",
        "/phieugiamgia/add",
        "/phieugiamgia/update",
        "/phieugiamgia/viewUpdate",
        "/phieugiamgia/delete",
        "/phieugiamgia/search",
        "/phieugiamgia/soft",
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
        } else if (uri.contains("search")) {
            Search(req, resp);
        } else if (uri.contains("soft")) {
            Soft(req, resp);
        } else if (uri.contains("login")) {
            LoginUI(req, resp);
        } else if (uri.contains("api/sinhvien/get-all")) {
            Ajax(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("add")) {
            Add(req, resp);
        } else if (uri.contains("update")) {
            Update(req, resp);
        } else if (uri.contains("login")) {
            Login(req, resp);
        }
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
        req.setAttribute("listSP", repository.getLoaiPhieuGiamGia());
        req.setAttribute("action", "hien-thi");
        req.setAttribute("page", page);
        req.setAttribute("totalPages", totalPages);
        req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
    }

    public void Add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String ma = req.getParameter("ma");
        Integer idGG = Integer.valueOf(req.getParameter("loaiPhieu"));
        LoaiPhieuGiamGia loaiPhieuGiamGia = repository.getLoaiPhieuGiamGiaID(idGG);
        String ten = req.getParameter("ten");
        Integer soLuong = Integer.valueOf(req.getParameter("soLuong"));

        PhieuGiamGia obj = new PhieuGiamGia(id, loaiPhieuGiamGia, ma, ten, soLuong, null, null, null);

        repository.add(obj);
        resp.sendRedirect(req.getContextPath() + "/phieugiamgia/hien-thi");
    }

    public void Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        repository.delete(id);
        resp.sendRedirect("/phieugiamgia/hien-thi");
    }

    public void ViewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        PhieuGiamGia sp = repository.getOne(id);
        req.setAttribute("listSP", repository.getLoaiPhieuGiamGia());
        req.setAttribute("sp", sp);
        req.setAttribute("action", "update");
        req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
    }

    public void Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String ma = req.getParameter("ma");
        Integer idGG = Integer.valueOf(req.getParameter("loaiPhieu"));
        LoaiPhieuGiamGia loaiPhieuGiamGia = repository.getLoaiPhieuGiamGiaID(idGG);
        String ten = req.getParameter("ten");
        Integer soLuong = Integer.valueOf(req.getParameter("soLuong"));

        PhieuGiamGia obj = new PhieuGiamGia(id, loaiPhieuGiamGia, ma, ten, soLuong, null, null, null);

        repository.update(obj);
        resp.sendRedirect("/phieugiamgia/hien-thi");
    }

    public void Search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        req.setAttribute("action", "hien-thi");
        req.setAttribute("danhSach", repository.search(keyword));
        req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
    }

    public void Soft(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", "hien-thi");
        req.setAttribute("danhSach", repository.soft());
        req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
    }

    public void LoginUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", "login");
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
    }

    public void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = null;

        if ("HangNT169".equals(username) && "123456".equals(password)) {
            role = "Admin";
        } else if ("TH03089".equals(username) && "SD20204".equals(password)) {
            role = "Student";
        }

        if (role != null) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            resp.sendRedirect(req.getContextPath() + "/phieugiamgia/hien-thi");
        } else {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
    }

        public void Ajax(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            }

}
