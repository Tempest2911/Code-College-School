package org.example.asmjava6.controller;

import org.example.asmjava6.entity.Category;
import org.example.asmjava6.repository.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
    @Autowired
    CategoryDAO categoryDAO;

    @GetMapping
    public List<Category> getAll() {
        return categoryDAO.findAll();
    }
}