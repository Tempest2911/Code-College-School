package org.example.thicuoimon_backend.repository;

import org.example.thicuoimon_backend.entity.DonDatHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DonDatHangRepo extends CrudRepository<DonDatHang, Integer> {

    @Query("select new org.example.thicuoimon_backend.repository.DonDatHangResponse(ddh.id, ddh.maDonHang, ddh.ngayDat, ddh.tongTien, sp.tenSanPham, sp.nhaSanXuat)" +
            "from DonDatHang ddh left join ddh.sanPham sp")
    List<DonDatHangResponse> getAllDonDatHang();

    @Query("select new org.example.thicuoimon_backend.repository.DonDatHangResponse(ddh.id, ddh.maDonHang, ddh.ngayDat, ddh.tongTien, sp.tenSanPham, sp.nhaSanXuat)" +
            "from DonDatHang ddh left join ddh.sanPham sp")
    Page<DonDatHangResponse> getPageDonDatHang(Pageable pageable);


}
