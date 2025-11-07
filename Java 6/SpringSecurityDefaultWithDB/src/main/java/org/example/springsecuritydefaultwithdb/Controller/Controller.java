package org.example.springsecuritydefaultwithdb.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping
    public String home(Model model) {
        model.addAttribute("message", "@/ => Home Page");
        return "page";
    }

    @RequestMapping("/poly/url1")
    public String method1(Model model) {
        model.addAttribute("message", "@/ => method1()");
        return "page";
    }

    @RequestMapping("/poly/url2")
    public String method2(Model model) {
        model.addAttribute("message", "@/ => method2()");
        return "page";
    }

    @RequestMapping("/poly/url3")
    public String method3(Model model) {
        model.addAttribute("message", "@/ => method3()");
        return "page";
    }

    @RequestMapping("/poly/url4")
    public String method4(Model model) {
        model.addAttribute("message", "@/ => method4()");
        return "page";
    }

}
