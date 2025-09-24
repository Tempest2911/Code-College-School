package org.example.buoi5;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class NguoiYeuController {

    private final NguoiYeuRepository repo = new NguoiYeuRepository();

    @GetMapping("/nguoi-yeu")
        public String xemDanhSachNguoiYeu(Model model) {
        List<NguoiYeu> NguoiYeu = repo.getNguoiYeu();
        model.addAttribute("DSNguoiYeu", NguoiYeu);
        return "nguoi-yeu";
        }

    @GetMapping("/nguoi-yeu/{nicknameny}")
        public String xemChiTietNguoiYeu(Model model, @PathVariable String nicknameny) {
            NguoiYeu nguoiYeu = repo.timNguoiYeuTheoTen(nicknameny);
            model.addAttribute("nguoiYeu", nguoiYeu);
            return "chi-tiet-nguoi-yeu";
        }
}
