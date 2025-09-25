package org.example.lab3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lab34/bai-1")
public class MonHocController {

    MonHocRepository repo = new MonHocRepository();

    @GetMapping
    public String xemDanhSachMonHoc(Model model) {
        List<MonHoc> NguoiYeu = repo.getMonHoc();
        model.addAttribute("DSMonHoc", NguoiYeu);
        return "/demo/hello";
    }

    @PostMapping("/add")
    public String AddMonHoc(@ModelAttribute MonHoc monHoc, Model model) {
        // thêm vào list
        repo.getMonHoc().add(monHoc);

        // load lại danh sách
        model.addAttribute("DSMonHoc", repo.getMonHoc());
        return "/demo/hello";
    }

    @GetMapping("/delete/{maMon}")
    public String delete(@PathVariable("maMon") String maMon, Model model) {
        MonHoc mh = repo.timMonHocTheoID(maMon);
        if (mh != null) {
            repo.getMonHoc().remove(mh); // xóa trực tiếp đối tượng tìm được
        }

        model.addAttribute("DSMonHoc", repo.getMonHoc());
        return "/demo/hello"; // load lại trang danh sách
    }



}
