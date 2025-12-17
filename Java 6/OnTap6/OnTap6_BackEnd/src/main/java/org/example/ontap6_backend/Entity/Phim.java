package org.example.ontap6_backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "phim")
public class Phim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @Nationalized
    @Column(name = "ten_phim", length = 200)
    private String tenPhim;

    @Size(max = 100)
    @Nationalized
    @Column(name = "dao_dien", length = 100)
    private String daoDien;

    @Size(max = 100)
    @Nationalized
    @Column(name = "the_loai", length = 100)
    private String theLoai;

    @Column(name = "nam_san_xuat")
    private Integer namSanXuat;

    @Column(name = "thoi_luong")
    private Integer thoiLuong;

    @Size(max = 50)
    @Nationalized
    @Column(name = "ngon_ngu", length = 50)
    private String ngonNgu;

    @Column(name = "diem_danh_gia")
    private Double diemDanhGia;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ghi_chu")
    private String ghiChu;

//    @OneToMany(mappedBy = "phim")
//    private Set<LichChieu> lichChieus = new LinkedHashSet<>();

}