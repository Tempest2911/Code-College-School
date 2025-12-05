package org.example.asmjava6.repository;

import org.example.asmjava6.entity.Order; // Import đúng Entity
import org.springframework.data.jpa.repository.JpaRepository;

// Sửa Product -> Order
public interface OrderDAO extends JpaRepository<Order, Long> {
    java.util.List<Order> findByUsername_Username(String username);
}