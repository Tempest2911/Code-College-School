package org.example.thicuoimon_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_san_pham", length = 150)
    private String tenSanPham;

    @Column(name = "ma_san_pham", length = 50)
    private String maSanPham;

    @Column(name = "loai_san_pham", length = 100)
    private String loaiSanPham;

    @Column(name = "nha_san_xuat", length = 100)
    private String nhaSanXuat;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ghi_chu")
    private String ghiChu;

}