package org.example.onthifinaljava4.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.equals("/login")) {
            login(req, resp);
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ((username == null || username.isBlank()) || (password == null || password.isBlank())) {
            req.setAttribute("eroi", "Username va password khong duoc trong" );
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }else if (!username.equals("HangNT169") || !password.equals("123456")) {
            req.setAttribute("eroi", "Vui long kiem tra lai thong tin" );
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            resp.sendRedirect("/san-pham/hien-thi");
        }
    }
}
