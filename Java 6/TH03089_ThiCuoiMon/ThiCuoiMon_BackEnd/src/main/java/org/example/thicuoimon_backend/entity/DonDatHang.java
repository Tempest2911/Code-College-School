package org.example.thicuoimon_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "don_dat_hang")
public class DonDatHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;

    @NotEmpty(message = "Mã đơn hàng không được để trống")
    @Column(name = "ma_don_hang", length = 50)
    private String maDonHang;

    @Column(name = "so_luong")
    private Integer soLuong;

    @NotEmpty(message = "Ngày đặt không được để trống")
    @Column(name = "ngay_dat")
    private String ngayDat;

    @NotNull(message = "Tổng tiền không được để trống")
    @Column(name = "tong_tien")
    private Double tongTien;

    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    @Column(name = "nguoi_dat", length = 100)
    private String nguoiDat;

    @Column(name = "dia_chi_giao", length = 200)
    private String diaChiGiao;

    @Column(name = "ghi_chu")
    private String ghiChu;

}