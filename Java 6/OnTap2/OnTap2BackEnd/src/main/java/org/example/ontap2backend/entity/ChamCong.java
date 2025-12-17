package org.example.ontap2backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "cham_cong")
public class ChamCong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;

    @NotEmpty(message = "Ngày chấm không được để trống")
    @Column(name = "ngay_cham")
    private String ngayCham;

    @Column(name = "gio_vao")
    private String gioVao;

    @Column(name = "gio_ra")
    private String gioRa;

    @NotNull(message = "Số giờ làm không được để trống")
    @Column(name = "so_gio_lam")
    private Double soGioLam;

    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "nguoi_xu_ly", length = 100)
    private String nguoiXuLy;

    @NotNull(message = "Phạt không được để trống")
    @Column(name = "phat")
    private Double phat;

}