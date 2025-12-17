package org.example.ontap6_backend.Repository;

import org.example.ontap6_backend.Entity.LichChieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LichChieuRepo extends CrudRepository<LichChieu, Integer> {

    @Query("select new org.example.ontap6_backend.Repository.LichChieuResponse(lc.id, lc.ngayChieu, lc.phongChieu, lc.giaVe, p.tenPhim, p.daoDien) " +
            "from LichChieu lc left join lc.phim p")
    List<LichChieuResponse> getAllLichChieu();

    @Query("select new org.example.ontap6_backend.Repository.LichChieuResponse(lc.id, lc.ngayChieu, lc.phongChieu, lc.giaVe, p.tenPhim, p.daoDien) " +
            "from LichChieu lc left join lc.phim p")
    Page<LichChieuResponse> getPage(Pageable pageable);


}
