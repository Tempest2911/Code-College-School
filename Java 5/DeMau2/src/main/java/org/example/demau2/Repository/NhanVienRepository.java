package org.example.demau2.Repository;

import org.example.demau2.Model.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    @Query("SELECT nv FROM NhanVien nv WHERE LOWER(nv.hoTen) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<NhanVien> searchByHoTen(@Param("keyword") String keyword);

    Page<NhanVien> findByHoTenContainingIgnoreCase(String keyword, Pageable pageable);
}
