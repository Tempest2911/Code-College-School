package org.example.ontap1.Controller;

import org.example.ontap1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public String view(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "usersView";
    }


}
