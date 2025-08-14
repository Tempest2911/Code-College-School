package org.example.onthifinaljava4.Servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.onthifinaljava4.Entity.SinhVien;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/sinh-vien/get-all")
public class AjaxSinhVien extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SinhVien> sv = new ArrayList<SinhVien>();
        sv.add(new SinhVien("Sv01", "Nguyen Van A", 18, true, "CNTT"));
        sv.add(new SinhVien("Sv02", "Nguyen Van B", 21, false, "TKDH"));
        sv.add(new SinhVien("Sv03", "Nguyen Van C", 43, false, "CNTT"));
        sv.add(new SinhVien("Sv04", "Nguyen Van D", 12, true, "TKDH"));
        sv.add(new SinhVien("Sv05", "Nguyen Van E", 34, true, "CNTT"));

        Gson gson = new Gson();
        String json = gson.toJson(sv);
        resp.setContentType("application/json");
        resp.getWriter().write(json);

    }
}
