package org.example.asmjava6.repository;

import org.example.asmjava6.entity.OrderDetail; // Import đúng Entity
import org.springframework.data.jpa.repository.JpaRepository;

// Sửa Product -> OrderDetail
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
}