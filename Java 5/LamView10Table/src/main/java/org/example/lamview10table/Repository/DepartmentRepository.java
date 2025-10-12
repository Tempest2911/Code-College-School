package org.example.lamview10table.Repository;

import org.example.lamview10table.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}

