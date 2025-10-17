package org.example.lab7.Controller;


import org.example.lab7.Model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private final org.example.lab7.service.StorageService storageService;
    private List<Product> products = new ArrayList<>();

    public ProductController(org.example.lab7.service.StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadProduct(@RequestParam String name,
                                @RequestParam double price,
                                @RequestParam("image") MultipartFile image) throws IOException {
        String imageUrl = storageService.saveFile(image);
        products.add(new Product(products.size() + 1, name, price, imageUrl));
        return "redirect:/products";
    }
}