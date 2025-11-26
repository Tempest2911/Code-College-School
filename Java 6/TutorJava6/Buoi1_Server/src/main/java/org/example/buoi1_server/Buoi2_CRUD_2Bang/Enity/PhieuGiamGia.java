package org.example.buoi1_server.Buoi2_CRUD_2Bang.Enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "phieu_giam_gia")
public class PhieuGiamGia {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "loai_phieu_id", nullable = false)
    private LoaiPhieuGiamGia loaiPhieu;

    @Column(name = "ma")
    private String ma;

    @Nationalized
    @Column(name = "ten")
    private String ten;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Nationalized
    @Column(name = "loai_giam")
    private String loaiGiam;

    @Column(name = "dieu_kieu_toi_thieu")
    private String dieuKieuToiThieu;

    @Column(name = "gia_tri_toi_da")
    private String giaTriToiDa;

}