package org.example.demotaco.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demotaco.Repository.Repository;
import org.example.demotaco.model.Taco;


import java.io.IOException;
import java.util.List;

@WebServlet("/lab")
public class Servlet extends HttpServlet {
    private final Repository simpleUserRepository = new Repository();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Taco> dsSU = simpleUserRepository.getAllTaco();

        request.setAttribute("dsTaco", dsSU);

        request.getRequestDispatcher("list-taco.jsp").forward(request, response);
    }
}

