package org.example.lab8.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.lab8.Model.*;
import org.example.lab8.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepo;
    private final PostViewRepository viewRepo;

    @GetMapping("/post")
    public String postList(@RequestParam(required=false) String filter, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<Post> posts = postRepo.findAll().stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed()).toList();

        Map<Long, Boolean> viewed = new HashMap<>();
        for (Post p : posts) viewed.put(p.getId(), viewRepo.existsByUserAndPost(user, p));

        if ("seen".equals(filter))
            posts = posts.stream().filter(p -> viewed.get(p.getId())).toList();
        else if ("unseen".equals(filter))
            posts = posts.stream().filter(p -> !viewed.get(p.getId())).toList();

        model.addAttribute("posts", posts);
        model.addAttribute("viewed", viewed);
        model.addAttribute("filter", filter);
        model.addAttribute("user", user);
        return "posts";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Post post = postRepo.findById(id).orElse(null);
        if (post == null) return "redirect:/post";

        if (!viewRepo.existsByUserAndPost(user, post))
            viewRepo.save(new PostView(null, user, post, java.time.LocalDateTime.now()));

        model.addAttribute("post", post);
        return "view";
    }
}
