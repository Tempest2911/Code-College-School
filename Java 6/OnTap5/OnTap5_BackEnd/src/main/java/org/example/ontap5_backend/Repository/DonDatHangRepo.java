package org.example.ontap5_backend.Repository;

import org.example.ontap5_backend.Entity.DonDatHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonDatHangRepo extends JpaRepository<DonDatHang, Integer> {

    @Query("select new org.example.ontap5_backend.Repository.DonDatHangResponse(dh.id, dh.maDonHang, dh.ngayDat, dh.tongTien, sp.tenSanPham, sp.nhaSanXuat)" +
            "from DonDatHang dh left join dh.sanPham sp")
            List<DonDatHangResponse> getAllDon();

    @Query("select new org.example.ontap5_backend.Repository.DonDatHangResponse(dh.id, dh.maDonHang, dh.ngayDat, dh.tongTien, sp.tenSanPham, sp.nhaSanXuat)" +
            "from DonDatHang dh left join dh.sanPham sp")
    Page<DonDatHangResponse> getPage(Pageable pageable);


}
