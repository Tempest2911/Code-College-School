package org.example.asm.Repository;

import org.example.asm.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // Tìm phòng ban theo tên
    Department findByName(String name);

    // Tìm tất cả phòng ban theo keyword (nếu muốn search)
    List<Department> findByNameContainingIgnoreCase(String keyword);
}