package org.example.asmjava6.repository;

import org.example.asmjava6.entity.Authority; // Import đúng Entity
import org.springframework.data.jpa.repository.JpaRepository;

// Sửa Product -> Authority
public interface AuthorityDAO extends JpaRepository<Authority, Integer> {
}