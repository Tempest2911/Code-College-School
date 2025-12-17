package org.example.ontap7_backend.repository;

import org.example.ontap7_backend.entity.DonHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DonHangRepo extends CrudRepository<DonHang, Integer> {

    @Query("select new org.example.ontap7_backend.repository.DonHangResponse(dh.id, dh.maDonHang, dh.ngayDat, dh.tongTien, kh.tenKhachHang, kh.diaChi) " +
            "from DonHang dh left join dh.khachHang kh")
    List<DonHangResponse> getAllDonHang();

    @Query("select new org.example.ontap7_backend.repository.DonHangResponse(dh.id, dh.maDonHang, dh.ngayDat, dh.tongTien, kh.tenKhachHang, kh.diaChi) " +
            "from DonHang dh left join dh.khachHang kh")
    Page<DonHangResponse> getPage(Pageable pageable);
}
