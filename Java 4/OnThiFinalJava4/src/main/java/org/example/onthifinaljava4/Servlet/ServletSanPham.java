package org.example.onthifinaljava4.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.onthifinaljava4.Entity.LoaiSP;
import org.example.onthifinaljava4.Entity.SanPham;
import org.example.onthifinaljava4.Repository.LoaiSanPhamRepo;
import org.example.onthifinaljava4.Repository.SanPhamRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletSanPham", value = {
        "/san-pham/hien-thi",
        "/san-pham/add",
        "/san-pham/detail",
        "/san-pham/update"
})
public class ServletSanPham extends HttpServlet {
    private final SanPhamRepo sanPhamRepo = new SanPhamRepo();
    private final LoaiSanPhamRepo loaiSanPhamRepo = new LoaiSanPhamRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.equals("/san-pham/hien-thi")) {
            hienthi(req, resp);
        }else if (uri.equals("/san-pham/detail")) {
            detail(req, resp);
        }
    }


    public void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        SanPham sanPham = sanPhamRepo.getSanPhamById(id);
        req.setAttribute("sanPham", sanPham);
        hienthi(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.equals("/san-pham/add")) {
            addSanPham(req, resp);
        }else if (uri.equals("/san-pham/update")) {
            update(req, resp);
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void addSanPham(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        String mota = req.getParameter("mota");
        Integer idloaisp = Integer.valueOf(req.getParameter("idloaisp"));
        LoaiSP loaiSP = loaiSanPhamRepo.getLoaiSanPhamById(idloaisp);
        SanPham sanPham = new SanPham(null, ma, ten, mota, null, null, null, loaiSP, null);
        sanPhamRepo.AddSanPham(sanPham);
        resp.sendRedirect("/san-pham/hien-thi");
    }

    public void hienthi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 0;
        int pageSize = 10;
        String pageStr = req.getParameter("page");
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }
        List<Object[]> list = sanPhamRepo.SpphanTrang(page, pageSize);
        List<LoaiSP> loaiSPS = loaiSanPhamRepo.getLoaiSanPham();
        req.setAttribute("loaiSanPham", loaiSPS);
        req.setAttribute("list", list);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/hien-thi.jsp").forward(req, resp);
    }
}
