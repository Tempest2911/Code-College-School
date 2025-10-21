package org.example.demau.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Nationalized
    @Column(name = "ma_nhan_vien", length = 20)
    @NotBlank(message = "Mã nhân viên không được để trống")
    private String maNhanVien;

    @Nationalized
    @Column(name = "ho_ten")
    @NotBlank(message = "Tên nhân viên không được để trống")
    @Pattern(
            regexp = "^[\\p{L}\\s]+$",
            message = "Tên nhân viên chỉ được chứa chữ và khoảng trắng, không được có số hoặc ký tự đặc biệt"
    )
    private String hoTen;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @Nationalized
    @Column(name = "email")
    private String email;

    @Column(name = "so_dien_thoai", length = 15)
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải có 10 chữ số")
    private String soDienThoai;

    @Column(name = "luong", precision = 18, scale = 2)
    private BigDecimal luong;

    @Nationalized
    @Column(name = "dia_chi")
    private String diaChi;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_chuc_vu")
    private ChucVu idChucVu;

}