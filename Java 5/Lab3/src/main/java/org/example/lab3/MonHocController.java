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
        repo.getMonHoc().add(monHoc);
        model.addAttribute("DSMonHoc", repo.getMonHoc());
        return "/demo/hello";
    }

    @GetMapping("/delete/{maMon}")
    public String delete(@PathVariable("maMon") String maMon, Model model) {
        MonHoc mh = repo.timMonHocTheoID(maMon);
        if (mh != null) {
            repo.getMonHoc().remove(mh);
        }
        model.addAttribute("DSMonHoc", repo.getMonHoc());
        return "/demo/hello";
    }

    @GetMapping("/edit/{maMon}")
    public String edit(@PathVariable("maMon") String maMon, Model model) {
        MonHoc mh = repo.timMonHocTheoID(maMon);
        if (mh != null) {
            model.addAttribute("monHoc", mh);
        }
        return "/demo/hello2";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MonHoc monHoc, Model model) {
        MonHoc mh = repo.timMonHocTheoID(monHoc.getMaMon());
        if (mh != null) {
            mh.setTenMon(monHoc.getTenMon());
            mh.setSoTinChi(monHoc.getSoTinChi());
            mh.setChuyenNghanh(monHoc.getChuyenNghanh());
            mh.setGiangVien(monHoc.getGiangVien());
            mh.setBatBuoc(monHoc.getBatBuoc());
        }
        model.addAttribute("DSMonHoc", repo.getMonHoc());
        return "/demo/hello";
    }

}
