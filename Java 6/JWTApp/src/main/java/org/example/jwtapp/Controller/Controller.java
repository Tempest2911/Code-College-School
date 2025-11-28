package org.example.jwtapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class Controller {
    @GetMapping("/poly/url0")
    public Object method0() {
        return Map.of("/poly/url0", "method0");
    }

    @GetMapping("/poly/url1")
    public Object method1() {
        return Map.of("/poly/url1", "method1");
    }

    @GetMapping("/poly/url2")
    public Object method2() {
        return Map.of("/poly/url2", "method2");
    }

    @GetMapping("/poly/url3")
    public Object method3() {
        return Map.of("/poly/url3", "method3");
    }


}
