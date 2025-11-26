// language: java
package org.example.buoi1_server.Buoi2_CRUD_2Bang.Repository;

import org.example.buoi1_server.Buoi2_CRUD_2Bang.Enity.PhieuGiamGia;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Response.PhieuGiamGiaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Integer> {

    @Query("""
                select new org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Response.PhieuGiamGiaResponse(
                    p.ma,
                    p.ten,
                    p.soLuong,
                    p.loaiGiam,
                    l.ma,
                    l.ten
                )
                from PhieuGiamGia p, LoaiPhieuGiamGia l
                where p.loaiPhieu.id = l.id
            """)
    List<PhieuGiamGiaResponse> hienThiDanhSachPhieuGiamGia();


    @Query("""
                select new org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Response.PhieuGiamGiaResponse(
                    p.ma,
                    p.ten,
                    p.soLuong,
                    p.loaiGiam,
                    l.ma,
                    l.ten
                )
                from PhieuGiamGia p, LoaiPhieuGiamGia l
                where p.loaiPhieu.id = l.id
                and p.id = ?1
            """)
    PhieuGiamGiaResponse detailPhieuGiamGia(Integer id);

    @Query("""
                select new org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Response.PhieuGiamGiaResponse(
                    p.ma,
                    p.ten,
                    p.soLuong,
                    p.loaiGiam,
                    l.ma,
                    l.ten
                )
                from PhieuGiamGia p, LoaiPhieuGiamGia l
                where p.loaiPhieu.id = l.id
            """)
    Page<PhieuGiamGiaResponse> phanTrangPhieuGiamGia(Pageable pageable);
}
