package org.example.lab8.Controller;

import lombok.RequiredArgsConstructor;
import org.example.lab8.Model.Subscriber;
import org.example.lab8.Repository.SubscriberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscriberRepository subRepo;

    @GetMapping("/subscribe")
    public String subscribeForm() { return "subscribe"; }

    @PostMapping("/subscribe")
    public String addEmail(@RequestParam String email, Model model) {
        Subscriber s = new Subscriber(null, email, UUID.randomUUID().toString());
        subRepo.save(s);
        model.addAttribute("msg", "Đăng ký nhận tin thành công: " + email);
        return "subscribe";
    }

    @GetMapping("/unsubscribe")
    public String unsubscribe(@RequestParam String token, Model model) {
        subRepo.findByToken(token).ifPresent(subRepo::delete);
        model.addAttribute("msg", "Hủy đăng ký thành công!");
        return "subscribe";
    }
}
