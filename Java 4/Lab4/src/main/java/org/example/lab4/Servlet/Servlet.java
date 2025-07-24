package org.example.lab4.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab4.Repository.Repository;
import org.example.lab4.model.SimpleUser;
import org.example.lab4.model.Contact;
import org.example.lab4.model.Product;
import org.example.lab4.model.Task;
import org.example.lab4.model.NewsletterSubscriber;
import java.io.IOException;
import java.util.List;

@WebServlet("/lab")
public class Servlet extends HttpServlet {
    private final Repository simpleUserRepository = new Repository();
    private final Repository contactRepository = new Repository();
    private final Repository productRepository = new Repository();
    private final Repository taskRepository = new Repository();
    private final Repository newsletterSubscriberRepository = new Repository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SimpleUser> dsSU = simpleUserRepository.getAllSimpleUser();
        List<Contact> dsC = contactRepository.getAllContact();
        List<Product> dsP = productRepository.getAllProduct();
        List<Task> dsT = taskRepository.getAllTask();
        List<NewsletterSubscriber> dsNLS = newsletterSubscriberRepository.getAllNewsletterSubscriber();

        request.setAttribute("dsSimpleUser", dsSU);
        request.setAttribute("dsContact", dsC);
        request.setAttribute("dsProduct", dsP);
        request.setAttribute("dsTask", dsT);
        request.setAttribute("dsNewsletterSubscriber", dsNLS);

        request.getRequestDispatcher("lab.jsp").forward(request, response);
    }
}

