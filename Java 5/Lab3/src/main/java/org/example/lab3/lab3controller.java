package org.example.lab3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RequestMapping("/staff")
@Controller
public class lab3controller {
    @GetMapping("/detail")
    public String detail(Model model) throws ParseException {
        String birthdayStr = "23/12/2024";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthday = sdf.parse(birthdayStr);
        Staff staff = Staff.builder()
                .id("user@gmail.com")
                .fullName("nguyễn văn user")
                .gender(true)
                .birthday(birthday)
                .level(2)
                .salary(12345.68)
                .build();
        model.addAttribute("staff", staff);
        return "/demo/detail";
    }

    @GetMapping("/list")
    public String listdetail(Model model) {
        List<Staff> staff = List.of(
                Staff.builder()
                        .id("user1@gmail.com")
                        .fullName("nguyễn văn user 1")
                        .salary(12345.68)
                        .build(),
                Staff.builder()
                        .id("user2@gmail.com")
                        .fullName("nguyễn văn user 2")
                        .salary(12345.68)
                        .build(),
                Staff.builder()
                        .id("user3@gmail.com")
                        .fullName("nguyễn văn user 3")
                        .salary(12345.68)
                        .build(),
                Staff.builder()
                        .id("user4@gmail.com")
                        .fullName("nguyễn văn user 4")
                        .salary(12345.68)
                        .build(),
                Staff.builder()
                        .id("user5@gmail.com")
                        .fullName("nguyễn văn user 5")
                        .salary(12345.68)
                        .build(),
                Staff.builder()
                        .id("user6@gmail.com")
                        .fullName("nguyễn văn user 6")
                        .salary(12345.68)
                        .build());
        model.addAttribute("list", staff);
        return "/demo/listdetail";
    }


    @GetMapping("/list2")
    public String list2(Model model) {
        List<Staff> staff = List.of(
                Staff.builder()
                        .fullName("nguyễn văn user 1")
                        .build(),
                Staff.builder()
                        .fullName("nguyễn văn user 2")
                        .build(),
                Staff.builder()
                        .fullName("nguyễn văn user 3")
                        .build(),
                Staff.builder()
                        .fullName("nguyễn văn user 4")
                        .build(),
                Staff.builder()
                        .fullName("nguyễn văn user 5")
                        .build(),
                Staff.builder()
                        .fullName("nguyễn văn user 6")
                        .build());
        model.addAttribute("list2", staff);
        return "/demo/listtable";
    }


    @GetMapping("/list3")
    public String list3(Model model) {
        List<Staff> staff = List.of(
                Staff.builder()
                        .id("user1@gmail.com")
                        .fullName("nguyễn văn user 1")
                        .build(),
                Staff.builder()
                        .id("user2@gmail.com")
                        .fullName("nguyễn văn user 2")
                        .build(),
                Staff.builder()
                        .id("user3@gmail.com")
                        .fullName("nguyễn văn user 3")
                        .build(),
                Staff.builder()
                        .id("user4@gmail.com")
                        .fullName("nguyễn văn user 4")
                        .build(),
                Staff.builder()
                        .id("user5@gmail.com")
                        .fullName("nguyễn văn user 5")
                        .build(),
                Staff.builder()
                        .id("user6@gmail.com")
                        .fullName("nguyễn văn user 6")
                        .build());
        model.addAttribute("list3", staff);
        return "/demo/list-radio-selected";
    }
}
