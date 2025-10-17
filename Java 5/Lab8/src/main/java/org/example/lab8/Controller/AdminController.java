package org.example.lab8.Controller;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.lab8.Model.Post;
import org.example.lab8.Model.Subscriber;
import org.example.lab8.Repository.PostRepository;
import org.example.lab8.Repository.SubscriberRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final PostRepository postRepo;
    private final SubscriberRepository subRepo;
    private final JavaMailSender mailSender;

    @GetMapping("/admin")
    public String adminPage(HttpSession session) {
        if (session.getAttribute("isAdmin") == null) return "redirect:/login";
        return "admin";
    }

    @PostMapping("/admin/create")
    public String createPost(@RequestParam String title, @RequestParam String content, HttpSession session) {
        if (session.getAttribute("isAdmin") == null) return "redirect:/login";

        Post post = new Post(null, title, content, java.time.LocalDateTime.now());
        postRepo.save(post);

        // Gửi mail tới subscriber
        subRepo.findAll().forEach(sub -> {
            try {
                MimeMessage msg = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(msg, true);
                helper.setTo(sub.getEmail());
                helper.setSubject("Bài viết mới: " + title);
                String linkUnsub = "http://localhost:11111/unsubscribe?token=" + sub.getToken();
                helper.setText("<h2>" + title + "</h2><p>" + content + "</p><hr>"
                        + "<a href='" + linkUnsub + "'>Hủy đăng ký</a>", true);
                mailSender.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return "redirect:/admin?success";
    }
}
