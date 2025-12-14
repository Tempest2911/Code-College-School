package org.example.ontap3_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotEmpty(message = "Mã nhân viên không được để trống")
    @Size(max = 20)
    @Nationalized
    @Column(name = "ma_nhan_vien", length = 20)
    private String maNhanVien;

    @NotEmpty(message = "Họ tên không được để trống")
    @Size(max = 255)
    @Nationalized
    @Column(name = "ho_ten")
    private String hoTen;

    @NotEmpty(message = "Ngày sinh không được để trống")
    @Column(name = "ngay_sinh")
    private String ngaySinh;

    @NotEmpty(message = "Giới tính không được để trống")
    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @NotEmpty(message = "Email không được để trống")
    @Size(max = 255)
    @Nationalized
    @Column(name = "email")
    private String email;

    @Size(max = 15)
    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Column(name = "luong", precision = 18, scale = 2)
    private BigDecimal luong;

    @Size(max = 255)
    @Nationalized
    @Column(name = "dia_chi")
    private String diaChi;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_chuc_vu")
    private ChucVu idChucVu;

}