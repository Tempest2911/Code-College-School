package org.example.final_sof3032.Repository;

import org.example.final_sof3032.Model.Xe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface XeRepository extends JpaRepository<Xe,Integer> {
    Page<Xe> findByTenXeContainingIgnoreCase(String keyword, Pageable pageable);
}
