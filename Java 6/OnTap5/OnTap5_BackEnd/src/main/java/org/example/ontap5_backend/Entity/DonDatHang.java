package org.example.ontap5_backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

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
    @Size(max = 50)
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

    @Size(max = 50)
    @Nationalized
    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    @Size(max = 100)
    @Nationalized
    @Column(name = "nguoi_dat", length = 100)
    private String nguoiDat;

    @Size(max = 200)
    @Nationalized
    @Column(name = "dia_chi_giao", length = 200)
    private String diaChiGiao;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ghi_chu")
    private String ghiChu;

}