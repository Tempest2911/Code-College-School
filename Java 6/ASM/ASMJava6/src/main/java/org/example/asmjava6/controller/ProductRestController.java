package org.example.asmjava6.controller;

import org.example.asmjava6.entity.Product;
import org.example.asmjava6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ... (import cũ)

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Integer id) { return productService.findById(id); }

    @GetMapping
    public List<Product> getAll() { return productService.findAll(); }

    // --- THÊM ĐOẠN NÀY ---
    @PostMapping // Tạo mới
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("{id}") // Cập nhật
    public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("{id}") // Xóa
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }
}