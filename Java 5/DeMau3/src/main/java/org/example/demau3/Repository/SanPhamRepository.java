package org.example.demau3.Repository;

import org.example.demau3.Model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {


    @Query("SELECT nv FROM SanPham nv WHERE LOWER(nv.tenSanPham) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<SanPham> searchBytenSanPham(@Param("keyword") String keyword);

    Page<SanPham> findBytenSanPhamContainingIgnoreCase(String keyword, Pageable pageable);

}
