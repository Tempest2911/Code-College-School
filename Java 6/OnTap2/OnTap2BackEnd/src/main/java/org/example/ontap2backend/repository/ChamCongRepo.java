package org.example.ontap2backend.repository;

import org.example.ontap2backend.entity.ChamCong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamCongRepo extends JpaRepository<ChamCong, Integer> {
    @Query("SELECT new org.example.ontap2backend.repository.ChamCongResponse(d.id, d.ngayCham, d.soGioLam, d.phat, k.tenNhanVien, k.phongBan) " +
            "FROM ChamCong d LEFT JOIN d.nhanVien k")
    List<ChamCongResponse> getAllCustom();

    @Query("SELECT new org.example.ontap2backend.repository.ChamCongResponse(d.id, d.ngayCham, d.soGioLam, d.phat, k.tenNhanVien, k.phongBan) " +
            "FROM ChamCong d LEFT JOIN d.nhanVien k")
    Page<ChamCongResponse> getPageCustom(Pageable pageable);
}