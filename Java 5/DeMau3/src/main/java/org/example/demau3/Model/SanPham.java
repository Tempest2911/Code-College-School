package org.example.demau3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "san_pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotBlank(message = "Mã sản phẩm không được để trống")
    @Column(name = "ma_san_pham", nullable = false, length = 20)
    @Nationalized
    private String maSanPham;

    @Size(max = 100)
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Pattern(
            regexp = "^[\\p{L}\\s]+$",
            message = "Tên sản phẩm chỉ được chứa chữ và khoảng trắng, không được có số hoặc ký tự đặc biệt"
    )
    @Column(name = "ten_san_pham", nullable = false, length = 100)
    @Nationalized
    private String tenSanPham;

    // ✅ Giá bán: không được trống, không âm, chỉ chấp nhận số và tối đa 2 chữ số thập phân
    @NotNull(message = "Giá bán không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá bán phải lớn hơn 0")
    @Digits(integer = 10, fraction = 2, message = "Giá bán chỉ được nhập số và tối đa 2 chữ số thập phân")
    @Column(name = "gia_ban", precision = 18, scale = 2)
    private BigDecimal giaBan;

    // ✅ Số lượng: không được trống, không âm, chỉ chấp nhận số nguyên
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    @Digits(integer = 10, fraction = 0, message = "Số lượng chỉ được nhập số nguyên, không được có chữ hoặc ký tự đặc biệt")
    @Column(name = "so_luong")
    private Integer soLuong;

    @NotNull(message = "Loại sản phẩm không được để trống")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_loai_san_pham", nullable = false)
    private LoaiSanPham idLoaiSanPham;

    @Size(max = 255)
    @Nationalized
    @Column(name = "mo_ta")
    private String moTa;
}
