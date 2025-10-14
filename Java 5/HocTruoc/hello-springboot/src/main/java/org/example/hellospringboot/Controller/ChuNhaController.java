    package org.example.hellospringboot.Controller;

    import org.example.hellospringboot.entity.ChuNha;
    import org.example.hellospringboot.repository.ChuNhaRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @Controller
    @RequestMapping("/chunha")
    public class ChuNhaController {

        @Autowired
        private ChuNhaRepository chuNhaRepository;

        // Danh sách
        @GetMapping
        public String list(Model model) {
            model.addAttribute("view", "list");
            model.addAttribute("chuNha", chuNhaRepository.findAll());
            return "demo/hello";
        }

        // Thêm mới
        @GetMapping("/add")
        public String showAddForm(Model model) {
            model.addAttribute("view", "form");
            model.addAttribute("chuNha", new ChuNha());
            return "demo/hello";
        }

        // Sửa
        @GetMapping("/edit/{id}")
        public String showEditForm(@PathVariable("id") Integer id, Model model) {
            model.addAttribute("view", "form");
            model.addAttribute("chuNha", chuNhaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id)));
            return "demo/hello";
        }

        // Lưu
        @PostMapping("/save")
        public String save(@ModelAttribute("chuNha") ChuNha chuNha) {
            chuNhaRepository.save(chuNha);
            return "redirect:/chunha";
        }

        // Xóa
        @GetMapping("/delete/{id}")
        public String delete(@PathVariable("id") Integer id) {
            chuNhaRepository.deleteById(id);
            return "redirect:/chunha";
        }


    }

