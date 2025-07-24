package org.example.templatesof3012.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/nam-quoc-son-ha")
public class NamQuocSonHa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().println("<h1>Nam Quốc Sơn Hà</h1>");
        resp.getWriter().println("Nam quốc sơn hà Nam đế cư,<br>");
        resp.getWriter().println("Tiệt nhiên định phận tại thiên thư.<br>");
        resp.getWriter().println("Như hà nghịch lỗ lai xâm phạm,<br>");
        resp.getWriter().println("Nhữ đẳng hành khan thủ bại hư.<br>");
    }
}