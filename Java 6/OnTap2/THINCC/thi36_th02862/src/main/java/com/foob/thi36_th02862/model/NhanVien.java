package com.foob.thi36_th02862.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Nationalized
    @Column(name = "ten_nhan_vien", length = 100)
    private String tenNhanVien;

    @Size(max = 100)
    @Nationalized
    @Column(name = "chuc_vu", length = 100)
    private String chucVu;

    @Size(max = 100)
    @Nationalized
    @Column(name = "phong_ban", length = 100)
    private String phongBan;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @Size(max = 200)
    @Nationalized
    @Column(name = "dia_chi", length = 200)
    private String diaChi;

    @Size(max = 20)
    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "luong")
    private Double luong;

    @Column(name = "trang_thai")
    private Boolean trangThai;

//    @OneToMany(mappedBy = "nhanVien")
//    private Set<ChamCong> chamCongs = new LinkedHashSet<>();

}