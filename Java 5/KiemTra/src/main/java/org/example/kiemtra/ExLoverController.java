package org.example.kiemtra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ex-lover")
public class ExLoverController {

    ExLoverRepository repo = new ExLoverRepository();

    @GetMapping
    public String xemDanhSachExLover(Model model) {
        List<ExLover> NguoiYeu = repo.getNguoiYeuCu();
        model.addAttribute("DSNguoiYeuCu", NguoiYeu);
        return "hello";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        ExLover mh = repo.timNguoiYeuCuTheoID(id);
        if (mh != null) {
            repo.getNguoiYeuCu().remove(mh);
        }
        model.addAttribute("DSNguoiYeuCu", repo.getNguoiYeuCu());
        return "hello";
    }

}
