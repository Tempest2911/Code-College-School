package org.example.buoi4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThoController {

    @GetMapping("/bai-tho-so-1")
    public String namQuocSonHa() {
        return "nam-quoc-son-ha";
    }

    @GetMapping("/bai-tho-so-2")
    public String canhKhuya() {
        return "canh-khuya";
    }


}
