package org.example.asmjava6.repository;

import org.example.asmjava6.entity.Category; // Import đúng Entity
import org.springframework.data.jpa.repository.JpaRepository;

// Sửa Product -> Category
public interface CategoryDAO extends JpaRepository<Category, String> {
}