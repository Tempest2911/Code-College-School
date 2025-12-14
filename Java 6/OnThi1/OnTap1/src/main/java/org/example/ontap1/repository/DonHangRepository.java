package org.example.ontap1.repository;

import org.example.ontap1.entity.DonHang;
import org.example.ontap1.repository.DonHangResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {

    @Query("SELECT new org.example.ontap1.repository.DonHangResponse(d.id, d.maDonHang, d.ngayDat, d.tongTien, k.tenKhachHang, k.diaChi) " +
            "FROM DonHang d LEFT JOIN d.khachHang k")
    List<DonHangResponse> getAllCustom();

    @Query("SELECT new org.example.ontap1.repository.DonHangResponse(d.id, d.maDonHang, d.ngayDat, d.tongTien, k.tenKhachHang, k.diaChi) " +
            "FROM DonHang d LEFT JOIN d.khachHang k")
    Page<DonHangResponse> getPageCustom(Pageable pageable);

}