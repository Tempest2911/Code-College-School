package org.example.asmjava6.repository;

import org.example.asmjava6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
    // Tìm sản phẩm theo CategoryId
    List<Product> findByCategory_Id(String categoryId);
}