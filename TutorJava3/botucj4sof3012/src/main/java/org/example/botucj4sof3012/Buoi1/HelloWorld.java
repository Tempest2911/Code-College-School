package org.example.botucj4sof3012.Buoi1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HelloWorld", value = {
        "/buoi1/hello", //GET
        "/buoi1/hienthi", //GET
        "/buoi1/dangnhap"  //POST
})
public class HelloWorld extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.contains("hello")) {
            helloWorld(request, response);
        }else if (uri.contains("hienthi")) {
            hienThi(request, response);
        }
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/buoi1/hienThi.jsp").forward(request, response);
    }

    private void helloWorld(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ten", "Nguyen Duy Phong");
        request.getRequestDispatcher("/buoi1/helloWorld.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/dangnhap")) {
            dangNhap(req, resp);
        }
    }


    private void dangNhap(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.getRequestDispatcher("/buoi1/thongTin.jsp").forward(req, resp);
    }
}
