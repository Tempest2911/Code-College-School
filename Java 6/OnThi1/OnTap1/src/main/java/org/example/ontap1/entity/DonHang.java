package org.example.ontap1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.validation.constraints.Size;
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
    private KhachHang khachHang;

    @NotBlank(message = "Mã đơn hàng không được để trống")
    @Size(max = 50)
    @Column(name = "ma_don_hang", length = 50)
    private String maDonHang;

    @Column(name = "ngay_dat")
    private LocalDate ngayDat;

    @Min(value = 0, message = "Tổng tiền phải lớn hơn hoặc bằng 0")
    @Column(name = "tong_tien")
    private Double tongTien;

    @Size(max = 200)
    @Nationalized
    @Column(name = "dia_chi_giao", length = 200)
    private String diaChiGiao;

    @Size(max = 20)
    @Column(name = "so_dien_thoai_giao", length = 20)
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