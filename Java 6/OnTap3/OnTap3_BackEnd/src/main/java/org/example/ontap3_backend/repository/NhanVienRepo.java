package org.example.ontap3_backend.repository;

import org.example.ontap3_backend.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepo extends JpaRepository<NhanVien, Integer> {
    @Query("SELECT new org.example.ontap3_backend.repository.NhanVienResponse(d.id, d.maNhanVien, d.hoTen, d.gioiTinh, d.ngaySinh, k.maChucVu, k.tenChucVu) " +
            "FROM NhanVien d LEFT JOIN d.idChucVu k")
    List<NhanVienResponse> getAllCustom();

    @Query("SELECT new org.example.ontap3_backend.repository.NhanVienResponse(d.id, d.maNhanVien, d.hoTen, d.gioiTinh, d.ngaySinh, k.maChucVu, k.tenChucVu) " +
            "FROM NhanVien d LEFT JOIN d.idChucVu k")
    Page<NhanVienResponse> getPageCustom(Pageable pageable);
}
