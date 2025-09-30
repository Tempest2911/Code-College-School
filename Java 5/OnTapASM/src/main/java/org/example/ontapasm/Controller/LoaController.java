package org.example.ontapasm.Controller;

import org.example.ontapasm.Model.Loa;
import org.example.ontapasm.Repository.LoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@Controller
public class LoaController {
//    Cách 1
//    private final LoaRepository repo = new LoaRepository();

    //Cách 2
    @Autowired
    private LoaRepository repo;


    @GetMapping("/loa")
    public String xemDS(Model model, @RequestParam(name = "keyword", defaultValue = "") String tuKhoa) {
        List<Loa> DS_Loa = repo.findAll();
        if ("".equals(tuKhoa)) {
            DS_Loa = repo.findAll();
        } else {
            DS_Loa = repo.findAllByTenLoa(tuKhoa);
        }
        model.addAttribute("DS_Loa", DS_Loa);
        return "loa-list";
    }


}
