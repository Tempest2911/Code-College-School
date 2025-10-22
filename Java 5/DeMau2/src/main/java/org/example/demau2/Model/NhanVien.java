package org.example.demau2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Size(max = 20)
    @Nationalized
    @Column(name = "ma_nhan_vien", length = 20)
    @NotBlank(message = "Mã nhân viên không được để trống")
    private String maNhanVien;

    @Size(max = 255)
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

    @Size(max = 255)
    @Nationalized
    @Column(name = "email")
    private String email;

    @Size(max = 15)
    @Column(name = "so_dien_thoai", length = 15)
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(\\+84|0)[0-9]{9}$",
            message = "Số điện thoại phải bắt đầu bằng 0 hoặc +84 và chỉ chứa chữ số, không có ký tự đặc biệt"
    )
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