package com.tc.bangkep.controller;

import com.tc.bangkep.model.NhanVien;
import com.tc.bangkep.model.PhongBan;
import com.tc.bangkep.repo.NhanVienRepo;
import com.tc.bangkep.repo.PhongBanRepo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.eclipse.tags.shaded.org.apache.xpath.operations.String;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "NhanVienController", value = {
        "/view",
        "/add",
        "/addView",
        "/delete",
        "/edit",
        "/editView",
        "/search"
})
public class SanPhamController extends HttpServlet {
    NhanVienRepo repository = new NhanVienRepo();
    PhongBanRepo repo = new PhongBanRepo();


    // GETTTTTTTTTTTTTTTT +============================================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/view":
                view(req, res);
                break;
            case "/addView":
                addView(req, res);
                break;
            case "/add":
                add(req, res);
                break;
            case "/delete":
                Integer id = Integer.valueOf(req.getParameter("id"));
                NhanVien nv = repository.getOne(id);
                repository.delete(nv);
                res.sendRedirect("/view");
                break;
            case "/edit":
                Integer idc = Integer.valueOf(req.getParameter("id"));
                String hotenc = req.getParameter("hoten");
                String chucvuc = req.getParameter("chucvu");
                Integer luongc = Integer.valueOf(req.getParameter("luong"));
                Boolean dangLamViecc = Boolean.valueOf(req.getParameter("dangLamViec"));
                PhongBan phongBanc = repo.getOne(Integer.valueOf(req.getParameter("phongBanId")));

                NhanVien object = new NhanVien(
                        idc,
                        hotenc,
                        chucvuc,
                        luongc,
                        dangLamViecc,
                        phongBanc
                );

                repository.update(object);
                res.sendRedirect("/view");
                break;
            case "/editView":
                Integer ida = Integer.valueOf(req.getParameter("id"));
                req.setAttribute("nhanVien", repository.getOne(ida));
                req.setAttribute("listPhongBan", repo.getAll());
                req.getRequestDispatcher("/edit.jsp").forward(req, res);
                break;
            case "/search":
                String searchTerm = req.getParameter("ten");
                List<NhanVien> sc = repository.getAll();
                List<NhanVien> result = sc.stream()
                        .filter(user -> user.getHoten().contains(searchTerm))
                        .collect(Collectors.toList());
                req.setAttribute("nv", result);
                req.getRequestDispatcher("/view.jsp").forward(req, res);
                break;

        }
    }


    private void addView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("listPhongBan", repo.getAll());
        req.getRequestDispatcher("/add.jsp").forward(req, res);
    }

    private void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String hoten = req.getParameter("hoten");
        String chucvu = req.getParameter("chucvu");
        Integer luong = Integer.valueOf(req.getParameter("luong"));
        Boolean dangLamViec = Boolean.valueOf(req.getParameter("dangLamViec"));
        PhongBan phongBan = repo.getOne(Integer.valueOf(req.getParameter("phongBanId")));

        NhanVien object = new NhanVien(
                id,
                hoten,
                chucvu,
                luong,
                dangLamViec,
                phongBan
        );

        repository.add(object);
        res.sendRedirect("/view");
    }

    private void view(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         req.setAttribute("nv", repository.getAll());
         req.getRequestDispatcher("/view.jsp").forward(req, res);
    }


    // POSTTTTTTTTTTTTT +============================================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

}