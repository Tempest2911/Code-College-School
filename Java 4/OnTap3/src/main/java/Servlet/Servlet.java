package Servlet;

import Model.ChucVu;
import Model.NhanVien;
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

@WebServlet(name = "NhanVienServlet", value = {
        "/nhanvien/hien-thi",
        "/nhanvien/add",
        "/nhanvien/update",
        "/nhanvien/viewUpdate",
        "/nhanvien/delete",
        "/nhanvien/soft",
        "/nhanvien/search",
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
        } else if (uri.contains("login")) {
            LoginUI(req, resp);
        } else if (uri.contains("api/sinhvien/get-all")) {
            Ajax(req, resp);
        } else if (uri.contains("soft")) {
            Soft(req, resp);
        } else if (uri.contains("search")) {
            Search(req, resp);
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
            req.setAttribute("listSP", repository.getChucVu());
            req.setAttribute("action", "hien-thi");
            req.setAttribute("page", page);
            req.setAttribute("totalPages", totalPages);
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
        }

        public void Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Integer id = Integer.valueOf(req.getParameter("id"));
            repository.delete(id);
            resp.sendRedirect("/nhanvien/hien-thi");
        }

        public void ViewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Integer id = Integer.valueOf(req.getParameter("id"));
            NhanVien sp = repository.getOne(id);
            req.setAttribute("listSP", repository.getChucVu());
            req.setAttribute("sp", sp);
            req.setAttribute("action", "update");
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
        }

        public void LoginUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setAttribute("action", "login");
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
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

        public void Soft(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setAttribute("action", "hien-thi");
            req.setAttribute("danhSach", repository.soft());
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
        }

        public void Search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String keyword = req.getParameter("keyword");
            req.setAttribute("action", "hien-thi");
            req.setAttribute("danhSach", repository.search(keyword));
            req.getRequestDispatcher("/sigma.jsp").forward(req, resp);
        }

        public void Add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String ma = req.getParameter("ma");
            String ten = req.getParameter("ten");
            String tenDem = req.getParameter("tenDem");
            String ho = req.getParameter("ho");
            String gioiTinh = req.getParameter("gioiTinh");
            String diaChi = req.getParameter("diaChi");
            String sdt = req.getParameter("sdt");
            String matKhau = req.getParameter("matKhau");
            Integer idCv = Integer.valueOf(req.getParameter("idCv"));
            ChucVu chucVu = repository.getChucVuID(idCv);
            Integer trangThai = Integer.valueOf(req.getParameter("trangThai"));

            NhanVien obj = new NhanVien(null, ma, ten, tenDem, ho, gioiTinh, diaChi, sdt, matKhau, chucVu, trangThai);

            repository.add(obj);
            resp.sendRedirect("/nhanvien/hien-thi");
        }

        public void Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Integer id = Integer.valueOf(req.getParameter("id"));
            String ma = req.getParameter("ma");
            String ten = req.getParameter("ten");
            String tenDem = req.getParameter("tenDem");
            String ho = req.getParameter("ho");
            String gioiTinh = req.getParameter("gioiTinh");
            String diaChi = req.getParameter("diaChi");
            String sdt = req.getParameter("sdt");
            String matKhau = req.getParameter("matKhau");
            Integer idCv = Integer.valueOf(req.getParameter("idCv"));
            ChucVu chucVu = repository.getChucVuID(idCv);
            Integer trangThai = Integer.valueOf(req.getParameter("trangThai"));

            NhanVien obj = new NhanVien(id, ma, ten, tenDem, ho, gioiTinh, diaChi, sdt, matKhau, chucVu, trangThai);

            repository.update(obj);
            resp.sendRedirect("/nhanvien/hien-thi");
        }

        public void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                resp.sendRedirect("/nhanvien/hien-thi");
            }
        }

}
