package org.example.asmjava6.service;


import org.example.asmjava6.entity.Product;
import org.example.asmjava6.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ... (các import cũ)

@Service
public class ProductService {
    @Autowired
    ProductDAO pdao;

    public List<Product> findAll() { return pdao.findAll(); }
    public Product findById(Integer id) { return pdao.findById(id).get(); }
    public List<Product> findByCategoryId(String cid) { return pdao.findByCategory_Id(cid); }

    // --- THÊM 3 HÀM NÀY ---
    public Product create(Product product) { return pdao.save(product); }
    public Product update(Product product) { return pdao.save(product); }
    public void delete(Integer id) { pdao.deleteById(id); }
}