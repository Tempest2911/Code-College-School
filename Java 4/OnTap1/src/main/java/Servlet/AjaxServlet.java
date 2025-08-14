package Servlet;

import Model.SinhVien;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/sinh-vien/get-all")
public class AjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
