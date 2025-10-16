package org.example.luyentaptrenlopbuoi9.Controller;

import org.example.luyentaptrenlopbuoi9.Model.Category;
import org.example.luyentaptrenlopbuoi9.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("category", new Category());
        return "categories";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));
        model.addAttribute("category", category);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("category") Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "id", required = false) Integer id,
                        @RequestParam(value = "code", required = false) String code,
                        Model model,
                        @RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Category> categoryPage;
        if (id != null) {
            categoryPage = categoryRepository.findByIdContaining(id, pageable);
        } else if (code != null && !code.isEmpty()) {
            categoryPage = categoryRepository.findByCategoryCodeContainingIgnoreCase(code, pageable);
        } else {
            categoryPage = categoryRepository.findAll(pageable);
        }
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("category", new Category());
        return "categories";
    }
}
