package org.example.ontap4_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "don_hang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id")
    @JsonIgnore
    private KhachHang khachHang;

    @Size(max = 50)
    @Column(name = "ma_don_hang", length = 50)
    @NotEmpty(message = "Mã đơn hàng không được để trống")
    private String maDonHang;

    @Column(name = "ngay_dat")
    @NotEmpty(message = "Ngày đặt không được để trống")
    private String ngayDat;

    @Column(name = "tong_tien")
    @NotNull(message = "Tổng tiền không được để trống")
    private Double tongTien;

    @Size(max = 200)
    @Nationalized
    @Column(name = "dia_chi_giao", length = 200)
    @NotEmpty(message = "Địa chỉ giao không được để trống")
    private String diaChiGiao;

    @Size(max = 20)
    @Column(name = "so_dien_thoai_giao", length = 20)
    @NotEmpty(message = "Số điện thoại giao không được để trống")
    private String soDienThoaiGiao;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ghi_chu")

    private String ghiChu;
    @Size(max = 50)
    @Nationalized
    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    @Size(max = 100)
    @Nationalized
    @Column(name = "nguoi_xu_ly", length = 100)
    private String nguoiXuLy;

}