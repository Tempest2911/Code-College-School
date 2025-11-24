package org.example.buoi1_server.Repository;

import org.example.buoi1_server.Enity.CaSi;
import org.example.buoi1_server.Model.Response.CaSiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaSiRepository extends JpaRepository<CaSi, Integer> {
    //Hien thi => Load data len table
    // Du lieu tra ra => response

    // xu ly convert trong repo
    @Query("""
        select new org.example.buoi1_server.Model.Response.CaSiResponse(
            c.id,
            c.tenCaSi,
            c.queQuan,
            c.tuoi,
            c.sdt
        )
        from CaSi c
""")
    List<CaSiResponse>hienThiDanhSachCaSi();

    @Query("""
        select new org.example.buoi1_server.Model.Response.CaSiResponse(
            c.id,
            c.tenCaSi,
            c.queQuan,
            c.tuoi,
            c.sdt
        )
        from CaSi c
        where c.id = ?1
""")
    CaSiResponse detailCaSi(Integer id);

    @Query("""
        select new org.example.buoi1_server.Model.Response.CaSiResponse(
            c.id,
            c.tenCaSi,
            c.queQuan,
            c.tuoi,
            c.sdt
        )
        from CaSi c
""")

    Page<CaSiResponse> hienThiDanhSachPhanTrang(Pageable pageable);
}
