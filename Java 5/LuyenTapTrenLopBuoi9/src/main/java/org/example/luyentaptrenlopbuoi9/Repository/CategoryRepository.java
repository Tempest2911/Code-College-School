package org.example.luyentaptrenlopbuoi9.Repository;

import org.example.luyentaptrenlopbuoi9.Model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByIdContaining(Integer id, Pageable pageable);
    Page<Category> findByCategoryCodeContainingIgnoreCase(String code, Pageable pageable);
}
