package org.example.asmjava6.repository;

import org.example.asmjava6.entity.Account; // Import đúng Entity
import org.springframework.data.jpa.repository.JpaRepository;

// Sửa Product -> Account
public interface AccountDAO extends JpaRepository<Account, String> {
}