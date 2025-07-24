package org.example.demo_web_hibernate.model.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo_web_hibernate.model.Model.Cupcake;
import org.example.demo_web_hibernate.model.Model.RetroGame;
import org.example.demo_web_hibernate.model.Model.TalentPet;
import org.example.demo_web_hibernate.model.Repository.Repository;

import java.io.IOException;
import java.util.List;

@WebServlet("/lab")
public class Servlet extends HttpServlet {
    private final Repository simpleUserRepository = new Repository();
    private final Repository contactRepository = new Repository();
    private final Repository productRepository = new Repository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cupcake> dsSU = simpleUserRepository.getAllCupcake();
        List<RetroGame> dsC = contactRepository.getAllRetroGame();
        List<TalentPet> dsP = productRepository.getAllPetsSortedByScore();

        request.setAttribute("dsCupCake", dsSU);
        request.setAttribute("dsRetroGame", dsC);
        request.setAttribute("dsTalentPet", dsP);

        request.getRequestDispatcher("nhanvien.jsp").forward(request, response);
    }
}

