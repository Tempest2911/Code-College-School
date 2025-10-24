package org.example.thicuoiki.Repository;

import org.example.thicuoiki.Model.Xe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface XeRepository extends JpaRepository<Xe,Integer> {
    @Query("SELECT nv FROM Xe nv WHERE LOWER(nv.tenXe) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Xe> searchByTenXe(@Param("keyword") String keyword);

    Page<Xe> findByTenXeContainingIgnoreCase(String keyword, Pageable pageable);
}
