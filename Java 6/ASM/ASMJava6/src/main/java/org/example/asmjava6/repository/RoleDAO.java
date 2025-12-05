package org.example.asmjava6.repository;

import org.example.asmjava6.entity.Role; // Import đúng Entity
import org.springframework.data.jpa.repository.JpaRepository;

// Sửa Product -> Role
public interface RoleDAO extends JpaRepository<Role, String> {
}